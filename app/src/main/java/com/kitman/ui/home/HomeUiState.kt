package com.kitman.ui.home

import com.kitman.data.model.Athlete

data class HomeUiState(
    val isLoading: Boolean = false,
    val athletes: List<Athlete> = emptyList(),
    val error: String? = null
)
