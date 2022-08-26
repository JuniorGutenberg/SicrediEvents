package com.orbital.home.data.details.remote.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

object DetailsRequest {
    @Parcelize
    class Body(
        @SerializedName("eventId") var eventId:String,
        @SerializedName("name") var name:String,
        @SerializedName("email") var email:String
        ):Parcelable
}