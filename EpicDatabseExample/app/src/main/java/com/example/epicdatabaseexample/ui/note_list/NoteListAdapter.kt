package com.example.epicdatabaseexample.ui.note_list

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.epicdatabaseexample.db.note.NoteEntity

class NoteListAdapter(
    private val onNoteClick: (noteId: Int) -> Unit,
    private val onIsCompletedClick: (note: NoteEntity) -> Unit
) : ListAdapter<NoteEntity, NoteViewHolder>(NoteDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = getItem(position)
        holder.title.text = note.title
        holder.itemView.setOnClickListener {
            onNoteClick.invoke(note.id)
        }
        holder.personName.text = note.personName
        holder.isCompleted.setOnClickListener {
            onIsCompletedClick.invoke(
                note.copy(
                    isCompleted = !note.isCompleted
                )
            )
        }
        val isCompletedChanged = note.isCompleted != holder.isCompleted.isChecked
        if (isCompletedChanged) {
            holder.isCompleted.isChecked = note.isCompleted
        }
    }

    override fun onBindViewHolder(
        holder: NoteViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
            return
        }
        val note = getItem(position)
        holder.isCompleted.setOnClickListener {
            onIsCompletedClick.invoke(
                note.copy(
                    isCompleted = !note.isCompleted
                )
            )
        }
        val notePayload = payloads.first() as NoteDiffUtilCallback.NotePayload
        if (notePayload.isCompleteChanged)
            holder.isCompleted.isChecked = note.isCompleted
    }
}