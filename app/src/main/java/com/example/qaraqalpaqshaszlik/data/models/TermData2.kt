package com.example.qaraqalpaqshaszlik.data.models

data class TermData2(
    val termId: String,
    val term: String,
    val ownerPath: String,
    val ownerId: String,
    val like: List<String>? = null,
    val dislike: List<String>? = null
)
