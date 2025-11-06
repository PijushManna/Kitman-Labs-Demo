package com.kitman.domain.usecase

import com.kitman.data.model.AthleteDetails
import com.kitman.data.repository.interfaces.AthletesRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.firstOrNull

class GetAthleteDetailsUseCase(
    private val athleteRepository: AthletesRepo
) {
    operator fun invoke(athleteId: Int): Flow<AthleteDetails?> = channelFlow {
        val athlete = athleteRepository.getAthleteById(athleteId).firstOrNull()
        if (athlete == null){
            trySend(null)
            close()
            return@channelFlow
        }
        val squads = athlete.squadIds?.mapNotNull {
            athleteRepository.getSquadById(it).firstOrNull()
        } ?: emptyList()

        trySend(
            AthleteDetails(
                athlete = athlete,
                squads = squads
            )
        )
    }
}