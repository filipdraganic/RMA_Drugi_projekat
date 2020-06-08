package com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.views.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.rs.raf.projekat2.filip_Draganic_RN542017.data.models.Notes
import com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.views.recycler.diff.NotesDiff
import com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.views.recycler.viewholders.NotesViewHolder
import com.rsrafprojekat1.Filip_Draganic_RN542017.R

class NotesAdapter (notesDiff: NotesDiff,
                    private val onArchiveClicked:(Notes) -> Unit,
                    private val onDeleteClicked:(Notes) -> Unit,
                    private val onEditClicked:(Notes) -> Unit) : ListAdapter<Notes, NotesViewHolder>(notesDiff) {

    private val archiveDugme: (Int) -> Unit ={
        if(it >= 0){
            val note = getItem(it)
            onArchiveClicked.invoke(note)
        }
    }

    private val deleteDugme: (Int) -> Unit ={
        if(it >= 0){
            val note = getItem(it)
            onDeleteClicked.invoke(note)
        }
    }

    private val editDugme: (Int) -> Unit ={
        if(it >= 0){
            val note = getItem(it)
            onEditClicked.invoke(note)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val containerView = layoutInflater.inflate(R.layout.notes_fragment, parent, false)
        return NotesViewHolder(containerView, archiveDugme, deleteDugme, editDugme)


    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val note = getItem(position)
        holder.bind(note)
    }


}