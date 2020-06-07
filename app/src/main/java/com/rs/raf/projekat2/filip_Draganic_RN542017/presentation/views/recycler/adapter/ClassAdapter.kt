package com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.views.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.rs.raf.projekat2.filip_Draganic_RN542017.data.models.Class
import com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.views.recycler.diff.ClassDiff
import com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.views.recycler.viewholders.ClassViewHolder
import com.rsrafprojekat1.Filip_Draganic_RN542017.R

class ClassAdapter (classDiff: ClassDiff) : ListAdapter<Class, ClassViewHolder>(classDiff) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val containerView = layoutInflater.inflate(R.layout.class_fragment, parent,false)
        return ClassViewHolder(containerView)
    }

    override fun onBindViewHolder(holder: ClassViewHolder, position: Int) {
        val cas = getItem(position)
        holder.bind(cas)

    }


}