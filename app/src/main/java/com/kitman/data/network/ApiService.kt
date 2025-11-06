package com.kitman.data.network

import com.kitman.data.model.Athlete
import com.kitman.data.model.LoginResponse
import com.kitman.data.model.Squad
import retrofit2.http.GET

interface ApiService {
    @GET("session.json")
    suspend fun getLoginResponse(): LoginResponse

    @GET("athletes.json")
    suspend fun getAthletes(): List<Athlete>

    @GET("squads.json")
    suspend fun getSquads(): List<Squad>
}
