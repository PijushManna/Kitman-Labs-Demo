package com.kitman.data.repository.impl

import com.kitman.data.base.BaseResponse
import com.kitman.data.model.Athlete
import com.kitman.data.model.Squad
import com.kitman.data.network.ApiService
import com.kitman.data.repository.interfaces.AthletesRepo
import com.kitman.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow

class AthletesRepoImpl(private val apiService: ApiService) : AthletesRepo {
    private var cachedAthletes: Map<Int, Athlete>? = null
    private var cachedSquads: Map<Int, Squad>? = null

    override fun getAthletes(): Flow<BaseResponse<List<Athlete>>> = channelFlow {
        trySend(BaseResponse.Loading)
        try {
            if (cachedAthletes == null) {
                val response = apiService.getAthletes()
                cachedAthletes = response.associateBy { it.id }
                trySend(BaseResponse.Success(response))
            }else{
                trySend(BaseResponse.Success(cachedAthletes!!.values.toList()))
            }
        } catch (e: Exception) {
            trySend(BaseResponse.Error(e.message ?: Constants.DEFAULT_ERROR))
        }
    }

    override fun getSquads(): Flow<BaseResponse<List<Squad>>>  = channelFlow {
        trySend(BaseResponse.Loading)
        try {
            if (cachedSquads == null) {
                val response = apiService.getSquads()
                cachedSquads = response.associateBy { it.id }
                trySend(BaseResponse.Success(response))
            }else{
                trySend(BaseResponse.Success(cachedSquads!!.values.toList()))
            }
        } catch (e: Exception) {
            trySend(BaseResponse.Error(e.message ?: Constants.DEFAULT_ERROR))
        }
    }

    override fun getAthleteById(id: Int): Flow<Athlete?> = channelFlow {
        try {
            if (cachedAthletes == null) {
                val response = apiService.getAthletes()
                cachedAthletes = response.associateBy { it.id }
            }
            trySend(cachedAthletes?.get(id))
        }catch (e: Exception) {
            trySend(null)
        }
    }

    override  fun getSquadById(id: Int): Flow<Squad?> = channelFlow{
        try {
            if (cachedSquads == null) {
                val response = apiService.getSquads()
                cachedSquads = response.associateBy { it.id }
            }
            trySend(cachedSquads?.get(id))
        }catch (e: Exception) {
            trySend(null)
        }
    }
}