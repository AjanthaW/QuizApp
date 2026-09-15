package com.ajantha.quizserver.entity


import jakarta.persistence.*

@Entity
@Table(name = "quotes")
class Quote(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    @Column(nullable = false)
    var quote: String,
    @Column(nullable = false)
    var author: String
)