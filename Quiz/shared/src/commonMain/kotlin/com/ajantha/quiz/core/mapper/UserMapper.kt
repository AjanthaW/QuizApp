package com.ajantha.quiz.core.mapper

import com.ajantha.quiz.core.session.User
import com.ajantha.quiz.data.remote.model.UserResponse

fun UserResponse.toDomain(): User {
    return User(
        id = id,
        email = email,
        name = name,
        profileUrl = profileUrl,
        phone = phone,
        country = country,
        bio = bio
    )
}