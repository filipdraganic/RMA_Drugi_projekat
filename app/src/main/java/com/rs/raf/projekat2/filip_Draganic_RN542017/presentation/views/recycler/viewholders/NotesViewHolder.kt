package com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.views.recycler.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.rs.raf.projekat2.filip_Draganic_RN542017.data.models.Notes
import com.rsrafprojekat1.Filip_Draganic_RN542017.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.notes_fragment.*

class NotesViewHolder (override val containerView :View,
                        onArchiveClick:(Int) -> Unit,
                        onDeleteClick:(Int) -> Unit,
                        onEditClick:(Int) -> Unit) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    init {
        archiveBtn.setOnClickListener{
            if(adapterPosition != RecyclerView.NO_POSITION)
                onArchiveClick.invoke(adapterPosition)
        }
        deleteBtn.setOnClickListener{
            if(adapterPosition != RecyclerView.NO_POSITION)
                onDeleteClick.invoke(adapterPosition)
        }
        editBtn.setOnClickListener{
            if(adapterPosition != RecyclerView.NO_POSITION)
                onEditClick.invoke(adapterPosition)
        }

    }

    fun bind(note: Notes){
        titleTV.text = note.title
        contentTV.text = note.content

        if(note.isArchived == 1){
            archiveBtn.setImageResource(R.drawable.ic_baseline_unarchive_24)
        }else{
            archiveBtn.setImageResource(R.drawable.ic_baseline_archive_24)
        }

    }

}



