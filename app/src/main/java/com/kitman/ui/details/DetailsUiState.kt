package com.kitman.ui.details

import com.kitman.data.model.AthleteDetails

data class DetailsUiState(
    val isLoading: Boolean = false,
    val athlete: AthleteDetails? = null,
    val error: String? = null
)
