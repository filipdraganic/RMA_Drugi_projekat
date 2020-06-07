package com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.views.recycler.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.rs.raf.projekat2.filip_Draganic_RN542017.data.models.Pacijent
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.notes_fragment.*

class NotesViewHolder (override val containerView :View, onKartonClicked : (Int) -> Unit,
                       onOtpustiClicked : (Int) -> Unit) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    init {
        kartonBtn.setOnClickListener {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                onKartonClicked.invoke(adapterPosition)
            }
        }
        otpustBtn.setOnClickListener {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                onOtpustiClicked.invoke(adapterPosition)
            }
        }

    }

    fun bind(pacijent: Pacijent){
        imeTV.text = pacijent.ime
        prezimeTV.text = pacijent.prezime
    }

}

