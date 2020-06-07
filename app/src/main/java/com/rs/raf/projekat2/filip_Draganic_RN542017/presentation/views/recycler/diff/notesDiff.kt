package com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.views.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import com.rs.raf.projekat2.filip_Draganic_RN542017.data.models.Notes

class NotesDiff :DiffUtil.ItemCallback<Notes>(){

    override fun areItemsTheSame(oldItem: Notes, newItem: Notes): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Notes, newItem: Notes): Boolean {
        return oldItem.id == newItem.id &&
                oldItem.isArchived == newItem.isArchived &&
                oldItem.content == newItem.content &&
                oldItem.creator == newItem.creator &&
                oldItem.dateCreated == newItem.dateCreated &&
                oldItem.title == newItem.title
    }


}