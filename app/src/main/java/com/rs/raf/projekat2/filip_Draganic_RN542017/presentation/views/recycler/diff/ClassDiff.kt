package com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.views.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import com.rs.raf.projekat2.filip_Draganic_RN542017.data.models.Class

class ClassDiff : DiffUtil.ItemCallback<Class>(){

    override fun areItemsTheSame(oldItem: Class, newItem: Class): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Class, newItem: Class): Boolean {
        return oldItem.id == newItem.id &&
                oldItem.dan == newItem.dan &&
                oldItem.nastavnik == newItem.nastavnik &&
                oldItem.grupe == newItem.grupe &&
                oldItem.termin == newItem.termin &&
                oldItem.tip == newItem.tip &&
                oldItem.ucionica == newItem.ucionica &&
                oldItem.predmet == newItem.predmet

    }

}