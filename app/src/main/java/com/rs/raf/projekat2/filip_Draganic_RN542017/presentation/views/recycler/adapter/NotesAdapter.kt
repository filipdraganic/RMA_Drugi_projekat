package com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.views.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.rs.raf.projekat2.filip_Draganic_RN542017.data.models.Pacijent
import com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.views.recycler.diff.PacijentDiff
import com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.views.recycler.viewholders.NotesViewHolder
import com.rsrafprojekat1.Filip_Draganic_RN542017.R

class NotesAdapter (pacijentDiff: PacijentDiff, private val onKartonClicked: (Pacijent) -> Unit,
                    private val onOtpustiClicked: (Pacijent) -> Unit) : ListAdapter<Pacijent, NotesViewHolder>(pacijentDiff) {

    private val kartonDugme: (Int) -> Unit = {
        if(it >= 0){
            val pacijent = getItem(it)
            onKartonClicked.invoke(pacijent)
        }
    }

    private val otpustiDugme: (Int) -> Unit = {
        if(it >= 0){
            val pacijent = getItem(it)
            onOtpustiClicked.invoke(pacijent)
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val containerView = layoutInflater.inflate(R.layout.notes_fragment, parent, false)
        return NotesViewHolder(containerView, kartonDugme, otpustiDugme)


    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val pacijent = getItem(position)
        holder.bind(pacijent)
    }


}