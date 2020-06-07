package com.rs.raf.projekat2.filip_Draganic_RN542017.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.sql.Date

@Parcelize
data class Notes (
    val id: Long,
    val creator: Long,
    val dateCreated: Date,
    var title: String,
    var content: String,
    var isArchived: Boolean
):Parcelable