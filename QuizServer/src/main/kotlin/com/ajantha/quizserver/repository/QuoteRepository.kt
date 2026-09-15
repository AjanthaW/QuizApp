package com.ajantha.quizserver.repository

import com.ajantha.quizserver.entity.Quote
import org.springframework.data.jpa.repository.JpaRepository

interface QuoteRepository : JpaRepository<Quote, Long>