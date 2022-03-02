package com.example.coedit.ui.tab

import com.example.coedit.data.model.Note

interface NotesProvider {
    fun getNotes(folder: String): List<Note>
}