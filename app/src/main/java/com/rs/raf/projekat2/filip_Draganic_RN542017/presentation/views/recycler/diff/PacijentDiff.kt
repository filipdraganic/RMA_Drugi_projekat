package com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.views.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import com.rs.raf.projekat2.filip_Draganic_RN542017.data.models.Pacijent

class PacijentDiff : DiffUtil.ItemCallback<Pacijent>(){
    override fun areItemsTheSame(oldItem: Pacijent, newItem: Pacijent): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Pacijent, newItem: Pacijent): Boolean {
        return oldItem.ime == newItem.ime &&
                oldItem.prezime == newItem.prezime &&
                oldItem.datumHospitalizacije == newItem.datumHospitalizacije &&
                oldItem.datumOtpustanja == newItem.datumOtpustanja &&
                oldItem.datumPrijema == newItem.datumPrijema &&
                oldItem.simptomi == newItem.simptomi
    }

}