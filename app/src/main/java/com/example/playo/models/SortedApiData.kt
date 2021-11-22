package com.example.playo.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SortedApiData (
    var title : String,
    var author : String,
    var description : String,
    var photo : String,
    var url : String
): Parcelable