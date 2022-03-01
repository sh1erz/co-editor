package com.example.coedit.ui.note

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.coedit.R
import com.example.coedit.databinding.ActivityNoteBinding

class NoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_note)
        binding.etNote.apply {
            post {
                binding.lines = "\n".repeat(
                    (height / lineHeight).let { if (lineCount > it) lineCount else it } - 1)
            }
        }
    }
}

const val DEBUG = "coedit_d"