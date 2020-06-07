package com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.views.recycler.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.rs.raf.projekat2.filip_Draganic_RN542017.data.models.Notes
import com.rs.raf.projekat2.filip_Draganic_RN542017.data.models.Pacijent
import com.rsrafprojekat1.Filip_Draganic_RN542017.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.notes_fragment.*
import timber.log.Timber

class NotesViewHolder (override val containerView :View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    init {

    }

    fun bind(note: Notes){
        titleTV.text = note.title
        contentTV.text = note.content

        if(note.isArchived){
            archiveBtn.setImageResource(R.drawable.ic_baseline_unarchive_24)
        }

    }

}



