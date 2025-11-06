package com.kitman.data.model

import com.google.gson.annotations.SerializedName

data class Athlete(
    val id: Int,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    val username: String,
    @SerializedName("squad_ids")
    val squadIds: List<Int>? = null,
    val image: Image
) {
    val fullName: String get() = "$firstName $lastName"
}

data class Image(
    val url: String
)