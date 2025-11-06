package com.kitman.data.model

data class AthleteDetails(
    val athlete: Athlete,
    val squads: List<Squad> = emptyList(),
)
