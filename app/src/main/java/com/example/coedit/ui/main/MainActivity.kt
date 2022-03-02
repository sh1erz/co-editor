package com.example.coedit.ui.main

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import com.example.coedit.R
import com.example.coedit.data.model.Note
import com.example.coedit.data.model.User
import com.example.coedit.databinding.ActivityMainBinding
import com.example.coedit.ui.tab.NotesProvider
import com.example.coedit.ui.tab.TabAdapter
import com.google.android.material.tabs.TabLayoutMediator

//todo NotesProvider to ViewModel
class MainActivity : FragmentActivity(), NotesProvider {
    private lateinit var binding: ActivityMainBinding
    private val adapter = TabAdapter(folders, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        with(binding) {
            pager.adapter = adapter
            TabLayoutMediator(tabLayout, pager) { tab, position ->
                //todo get tab names
                tab.text = folders[position]
            }.attach()
        }
    }


    override fun getNotes(folder: String): List<Note> = when (folder) {
        "All" -> notes
        "Book" -> notes.filter { it.folder == "Book" }
        "Shareable" -> notes.filter { it.members.isNotEmpty() }
        else -> throw Exception("wrong folder: $folder")
    }

    companion object {
        val folders = listOf("All", "Book", "Shareable")
        val users = listOf(
            User("user1", ""),
            User("lox", "")
        )
        val notes = listOf(
            Note(0, users[0], "Lotr", listOf(), "25 Feb", "cool book, but boring", "Book"), //Book
            Note(1, users[1], "Mushoku discussion", listOf(users[0]), "15:00","Book"), //Book, Shareable
            Note(
                2,
                users[0],
                "Something",
                listOf(),
                text ="nothing interesting\nbut two lines\nor three\nor long long long fourth longer than this maybe",
                creationDate = "28 Feb"
            )  //
        )

    }
}