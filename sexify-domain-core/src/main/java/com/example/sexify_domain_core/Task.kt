package com.example.sexify_domain_core

data class Task(
    val id: Int,
    val text: String,
    val stages: Set<GameStage>,
    val orientations: Set<SexOrientation>,
    val performerGender: Set<Gender>,
    val timeSec: Long? = null,
)