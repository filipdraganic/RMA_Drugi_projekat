package com.rs.raf.projekat2.filip_Draganic_RN542017.extensions

import android.content.res.Resources

fun Int.toDp() : Int = (this / Resources.getSystem().displayMetrics.density).toInt()

fun Int.toPx() : Int = (this * Resources.getSystem().displayMetrics.density).toInt()
