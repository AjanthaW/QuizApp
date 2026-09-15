package com.ajantha.quiz.presentation.home.quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajantha.quiz.core.mapper.toModel
import com.ajantha.quiz.domain.model.QuizMode
import com.ajantha.quiz.domain.model.QuizSession
import com.ajantha.quiz.domain.store.QuizModeStore
import com.ajantha.quiz.domain.usecase.StartQuizUseCase
import com.ajantha.quiz.domain.usecase.SubmitAnswerUseCase
import com.ajantha.quiz.presentation.home.quiz.model.QuizUiState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class QuizViewModel(
    private val startQuizUseCase: StartQuizUseCase,
    private val submitAnswerUseCase: SubmitAnswerUseCase,
    private val quizModeStore: QuizModeStore
) : ViewModel() {

    val uiState: StateFlow<QuizUiState>
        field = MutableStateFlow(
            QuizUiState(
                mode = quizModeStore.mode.value
            )
        )

    val effect: SharedFlow<QuizEffect>
        field = MutableSharedFlow(extraBufferCapacity = 1)

    private var session: QuizSession? = null

    private var started = false

    init {
        observeQuizMode()
    }

    fun onIntent(intent: QuizIntent) {
        when (intent) {
            QuizIntent.Start -> start()
            is QuizIntent.SelectAnswer -> submitAnswer(intent.answer)
            QuizIntent.Continue -> continueQuiz()
            QuizIntent.StartAgain -> startQuiz(uiState.value.mode)
            is QuizIntent.ChangeMode -> changeMode(intent.mode)
            QuizIntent.ClearError -> clearError()
        }
    }

    private fun observeQuizMode() {
        viewModelScope.launch {
            quizModeStore.mode
                .collect { mode ->
                    if (!started) return@collect
                    if (mode != uiState.value.mode) {
                        startQuiz(mode)
                    }
                }
        }
    }

    private fun start() {
        if (started) return
        started = true
        startQuiz(mode = quizModeStore.mode.value)
    }

    private fun changeMode(mode: QuizMode) {
        if (mode == uiState.value.mode) return
        quizModeStore.setMode(mode)
    }

    private fun startQuiz(mode: QuizMode) {
        viewModelScope.launch {
            session = null

            uiState.update {
                QuizUiState(
                    isLoading = true,
                    mode = mode
                )
            }

            runCatching {
                startQuizUseCase(mode)
            }.onSuccess { newSession ->
                session = newSession

                uiState.update {
                    it.copy(
                        isLoading = false,
                        mode = newSession.mode,
                        totalQuestions = newSession.questions.size,
                        questionNumber = 1,
                        question = newSession.questions
                            .firstOrNull()
                            ?.toModel()
                    )
                }
            }
                .onFailure { error ->
                    uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = error.message ?: "Failed to start quiz"
                        )
                    }
                }
        }
    }

    private fun submitAnswer(answer: String) {
        val state = uiState.value
        val currentSession = session ?: return
        val question = currentQuestion() ?: return

        if (
            state.isLoading ||
            state.isSubmitting ||
            state.showAnswerDialog ||
            state.completed
        ) {
            return
        }

        viewModelScope.launch {
            uiState.update {
                it.copy(
                    isSubmitting = true,
                    selectedAnswer = answer,
                    errorMessage = null
                )
            }

            runCatching {
                submitAnswerUseCase(
                    sessionId = currentSession.id,
                    questionId = question.id,
                    answer = answer
                )
            }.onSuccess { result ->
                uiState.update {
                    it.copy(
                        isSubmitting = false,
                        selectedAnswer = answer,
                        answerCorrect = result.correct,
                        correctAnswer = result.correctAnswer,
                        correctAnswers = result.correctAnswers,
                        completed = result.completed,
                        showAnswerDialog = true
                    )
                }
            }
                .onFailure { error ->
                    uiState.update {
                        it.copy(
                            isSubmitting = false,
                            selectedAnswer = null,
                            errorMessage = error.message ?: "Failed to submit answer"
                        )
                    }
                }
        }
    }

    private fun continueQuiz() {
        val state = uiState.value

        if (!state.showAnswerDialog) return

        if (state.completed) {
            viewModelScope.launch {
                effect.emit(
                    QuizEffect.NavigateToResult(
                        correctAnswers = state.correctAnswers,
                        totalQuestions = state.totalQuestions
                    )
                )
            }
            return
        }

        val currentSession = session ?: return
        val nextIndex = state.questionNumber
        val nextQuestion = currentSession.questions.getOrNull(nextIndex) ?: return

        uiState.update {
            it.copy(
                question = nextQuestion.toModel(),
                questionNumber = nextIndex + 1,
                selectedAnswer = null,
                showAnswerDialog = false,
                answerCorrect = false,
                correctAnswer = ""
            )
        }
    }

    private fun currentQuestion() = session?.questions?.getOrNull(uiState.value.questionNumber - 1)

    private fun clearError() {
        uiState.update {
            it.copy(
                errorMessage = null
            )
        }
    }
}