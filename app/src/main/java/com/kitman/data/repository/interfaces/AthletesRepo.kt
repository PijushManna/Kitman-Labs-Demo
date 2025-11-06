package com.kitman.data.repository.interfaces

import com.kitman.data.base.BaseResponse
import com.kitman.data.model.Athlete
import com.kitman.data.model.Squad
import kotlinx.coroutines.flow.Flow

interface AthletesRepo {
    fun getAthletes() : Flow<BaseResponse<List<Athlete>>>
    fun getSquads() : Flow<BaseResponse<List<Squad>>>
    fun getAthleteById(id: Int): Flow<Athlete?>
    fun getSquadById(id: Int): Flow<Squad?>
}