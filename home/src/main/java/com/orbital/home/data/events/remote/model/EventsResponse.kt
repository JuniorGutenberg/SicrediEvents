package com.orbital.home.data.events.remote.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

object EventsResponse {
    @Keep
    @Parcelize
    class Body(
        @SerializedName("id") var id:String?,
        @SerializedName("title") var title:String?,
        @SerializedName("price") var price:Double?,
        @SerializedName("latitude") var latitude:Double?,
        @SerializedName("longitude") var longitude:Double?,
        @SerializedName("image") var image:String?,
        @SerializedName("description") var description:String?,
        @SerializedName("date") var date:Long?
    ):Parcelable
}