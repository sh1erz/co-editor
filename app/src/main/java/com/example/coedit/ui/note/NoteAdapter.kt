package com.example.coedit.ui.note

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coedit.data.model.Note
import com.example.coedit.databinding.ItemNoteBinding

class NoteAdapter(val notes: List<Note>, private val onNoteListener: OnNoteListener) :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            ItemNoteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onNoteListener
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(notes[position])
    }

    override fun getItemCount(): Int = notes.size

    class NoteViewHolder(val binding: ItemNoteBinding, private val onNoteListener: OnNoteListener) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(note: Note) {
            with(binding) {
                title.text = note.title
                note.text?.let { textPreview.text = it }
                creationTime.text = note.creationDate
                if (!note.members.isNullOrEmpty()) {
                    imgGroup.visibility = View.VISIBLE
                }
                root.setOnClickListener { onNoteListener.onNoteClick(note.id) }
            }
        }
    }

    interface OnNoteListener {
        fun onNoteClick(noteId: Int)
    }
}