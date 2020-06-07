package com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.views.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.rs.raf.projekat2.filip_Draganic_RN542017.data.models.Notes
import com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.views.recycler.diff.NotesDiff
import com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.views.recycler.viewholders.NotesViewHolder
import com.rsrafprojekat1.Filip_Draganic_RN542017.R

class NotesAdapter (notesDiff: NotesDiff) : ListAdapter<Notes, NotesViewHolder>(notesDiff) {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val containerView = layoutInflater.inflate(R.layout.notes_fragment, parent, false)
        return NotesViewHolder(containerView)


    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val note = getItem(position)
        holder.bind(note)
    }


}