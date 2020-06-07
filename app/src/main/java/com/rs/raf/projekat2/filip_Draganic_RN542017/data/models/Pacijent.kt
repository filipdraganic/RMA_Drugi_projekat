package com.rs.raf.projekat2.filip_Draganic_RN542017.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Pacijent(
    val id:UUID,
    var ime: String,
    var prezime: String,
    val datumPrijema: Date,
    var simptomi: String,
    val originalniSimptomi: String,
    var datumHospitalizacije:Date?,
    var datumOtpustanja:Date?

) : Parcelable