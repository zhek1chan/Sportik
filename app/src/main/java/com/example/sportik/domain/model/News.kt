package com.example.sportik.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class News(
    val id: Int,
    val title: String,
    val commentCount: String,
    val socialImage: String,
    val postedTime: String
) : Parcelable
