package com.example.coedit.ui.note

import androidx.lifecycle.ViewModel
import com.example.coedit.data.model.Note
import com.example.coedit.data.model.User

class NoteViewModel : ViewModel() {
    val note = Note(0, User("author", ""), "Title", listOf(), "22 Feb", "text", folder = "Books")
}