package com.example.coedit

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.coedit.data.model.Note
import com.example.coedit.data.model.User
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RealtimeDatabaseTest {

    val note = Note(
        0,
        author = User("lox", "lox"),
        title = "whatever",
        members = listOf(),
        creationDate = "3",
        text = null
    )
    val noteRDB = NoteRDB("text text", 7)


    @Test
    fun useAppContext() {
        val database = Firebase.database("http://localhost:9000/?ns=coedit-9956f")
        database.useEmulator("10.0.2.2", 9000)
        val databaseRefs = database.reference
        Log.i(DB_TEST, "databaseUrl: ${databaseRefs.database.app.options.databaseUrl}")

       /* database
            .getReference(note.id.toString())
            .setValue(noteRDB)
            .addOnFailureListener {
                Log.i(DB_TEST, "Failed ${it.message}")
            }
            .addOnSuccessListener {
                Log.i(DB_TEST, "Success ")
            }*/
        databaseRefs.setValue(null).addOnFailureListener { Log.i(DB_TEST, "Failed ${it.message}") }
        Log.i(DB_TEST, "after: ${databaseRefs.root}")
        assert(true)
    }

    companion object {
        const val namespace = "?ns=coedit-9956f"
        const val host = "http://10.0.2.2:9000/"
        const val DB_TEST = "coedit_test_db"
    }
}

data class NoteRDB(val text: String, val index: Int)