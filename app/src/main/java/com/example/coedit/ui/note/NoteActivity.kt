package com.example.coedit.ui.note

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.coedit.R
import com.example.coedit.data.model.Note
import com.example.coedit.data.model.User
import com.example.coedit.databinding.ActivityNoteBinding
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class NoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteBinding
    private val noteViewModel by viewModels<NoteViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_note)

        initNote(intent)

    }

    private fun initNote(intent: Intent){

        val noteId = intent.getIntExtra(NOTE_ID, -1)
        if (noteId == -1){
            binding.textEt.apply {
                post {
                    setText("\n".repeat(
                        (height / lineHeight).let { if (lineCount > it) lineCount else it } - 1))
                }
            }
            return
        }

        //todo get note
        val note = noteViewModel.note
        binding.textEt.setText(note.text)
        binding.titleEt.setText(note.title)
    }

    fun setText(){

    }


    /*
    RDB Structure
    notes:{
        0:{
            event1:{
                text : "text"
                index: 24
            }
            event2:{
                text : "text"
                index: 24
            }
            etc...
        }
    }
     */
    data class NoteRDB(val text: String, val index: Int)

    fun useAppContext() {
        val DB_TEST = "coedit_test_db"
        val note = Note(
            0,
            author = User("lox", "lox"),
            title = "whatever",
            members = listOf(),
            creationDate = "3",
            text = null
        )
        //todo put db url in properties
        val dbUrl = "http://localhost:9000/?ns=coedit-9956f"
        var noteRDB = NoteRDB("text text", 7)
        val database = Firebase.database(dbUrl)
        database.useEmulator("10.0.2.2", 9000)
        val databaseRefs = database.reference
        Log.i(DB_TEST, "databaseUrl: ${databaseRefs.database.app.options.databaseUrl}")

        //update note
        for (i in 0..1){
            databaseRefs
                .child(note.id.toString()) //note
                .push()  //event
                .setValue(noteRDB)
                .addOnFailureListener {
                    Log.i(DB_TEST, "Failed ${it.message}")
                }
                .addOnSuccessListener {
                    Log.i(DB_TEST, "Success ")
                }
            noteRDB = NoteRDB("text new", 2)
        }

        /* databaseRefs.setValue(null).addOnFailureListener { Log.i(DB_TEST, "Failed ${it.message}") }
             .addOnSuccessListener {
                 Log.i(DB_TEST, "Success ")
             }*/
    }

    companion object {
        const val NOTE_ID = "note_id"
        const val ACTION_CREATE_NOTE = "action_create_note"
        const val ACTION_OPEN_NOTE = "action_open_note"
    }
}

const val DEBUG = "coedit_d"