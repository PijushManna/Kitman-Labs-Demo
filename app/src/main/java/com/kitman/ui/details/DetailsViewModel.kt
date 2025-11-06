package com.kitman.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kitman.domain.usecase.GetAthleteDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailsViewModel(private val athleteDetailsUseCase: GetAthleteDetailsUseCase) : ViewModel() {
    private val _uiState = MutableStateFlow(DetailsUiState())
    val uiState: StateFlow<DetailsUiState> = _uiState

    fun getAthleteDetails(id: Int) {
        viewModelScope.launch {
            athleteDetailsUseCase(id)
                .onStart {
                    _uiState.update { DetailsUiState(isLoading = true) }
                }.catch {
                    _uiState.update { DetailsUiState(error = it.error) }
                }.collect { athleteDetails ->
                    if (athleteDetails == null) _uiState.update { DetailsUiState(error = "No athlete found") }
                    _uiState.update { DetailsUiState(athlete = athleteDetails) }
                }
        }
    }
}