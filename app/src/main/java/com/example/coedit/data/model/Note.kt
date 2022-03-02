package com.example.coedit.data.model

data class Note(
    val id: Int,
    val author: User,
    val title: String,
    val members: List<User>,
    //todo creationDate not string
    val creationDate : String,
    val text: String? = null,
    //todo what if folder deleted, what with notes in there?
    val folder: String? = null,
)
