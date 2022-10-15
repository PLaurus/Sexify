package com.lauruscorp.sexify_data.entities

data class Player(
    val id: Long,
    val name: String,
    val sex: SexifySex
)