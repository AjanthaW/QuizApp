package com.ajantha.quizserver.entity

import jakarta.persistence.*

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column(nullable = false, unique = true)
    val email: String,
    @Column(nullable = false)
    val password: String,
    @Column(nullable = false)
    val name: String,
    @Column(name = "profile_url")
    val profileUrl: String? = null,
    @Column
    val phone: String? = null,
    @Column
    val country: String? = null,
    @Column(length = 500)
    val bio: String? = null
)