package com.example.coedit.ui.tab

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coedit.data.model.Note
import com.example.coedit.databinding.ItemTabBinding
import com.example.coedit.ui.note.NoteAdapter

class TabAdapter(val folders: List<String>, val notesProvider: NotesProvider) :
    RecyclerView.Adapter<TabAdapter.TabViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TabViewHolder {
        return TabViewHolder(
            ItemTabBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TabViewHolder, position: Int) {
        val notes = notesProvider.getNotes(folders[position])
        holder.bind(notes)
    }

    override fun getItemCount(): Int = folders.size

    class TabViewHolder(private val binding: ItemTabBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(notes: List<Note>) {
            //for each tab new recycler -> adapter is new too
            binding.noteRecycler.adapter = NoteAdapter(notes)
        }
    }
}