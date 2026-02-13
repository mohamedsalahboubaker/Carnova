package com.salah.carnova.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class CarModel(
    val title:String="",
    val description: String="",
    val totalCapacity: String="",
    val highestSpeed : String="",
    val engineOutput: String="",
    val picUrl: String="",
    val price:Double=0.0,
    val rating: Double=0.0

): Parcelable
