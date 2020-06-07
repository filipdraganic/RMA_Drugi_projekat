package com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.views.recycler.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.rs.raf.projekat2.filip_Draganic_RN542017.data.models.Class
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.class_fragment.*

class ClassViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer{

    init {

    }

    fun bind(cas: Class){
        imeTV.text = cas.predmet + "-" + cas.tip
        grupeTV.text = cas.grupe
        daniVremeTV.text = cas.dan + "\n" + cas.termin
        ucionicaTV.text = cas.ucionica
        predavacTV.text = cas.nastavnik

    }

}