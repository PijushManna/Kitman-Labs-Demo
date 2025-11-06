package com.kitman.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kitman.data.base.BaseResponse
import com.kitman.data.repository.interfaces.AthletesRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val repo: AthletesRepo): ViewModel() {
    val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    fun getAthletes(){
        viewModelScope.launch {
            repo.getAthletes().collect {
                _uiState.value = when(it){
                    is BaseResponse.Success -> HomeUiState(athletes = it.data)
                    is BaseResponse.Error -> HomeUiState(error = it.message)
                    BaseResponse.Loading -> HomeUiState(isLoading = true)
                }
            }
        }
    }
}