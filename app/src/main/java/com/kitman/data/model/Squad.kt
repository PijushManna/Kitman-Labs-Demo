package com.kitman.data.model

import com.google.gson.annotations.SerializedName

data class Squad(
    val id: Int,
    val name: String,
    @SerializedName("organisation_id")
    val organisationId: Int,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String
)