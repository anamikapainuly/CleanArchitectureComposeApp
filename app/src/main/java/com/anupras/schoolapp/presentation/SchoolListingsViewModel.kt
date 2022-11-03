package com.anupras.schoolapp.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anupras.schoolapp.domain.repository.SchoolRepository
import com.anupras.schoolapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by anamika on 03,November,2022
 */

@HiltViewModel
class SchoolListingsViewModel @Inject constructor(
    private val repository: SchoolRepository
): ViewModel() {

    var state by mutableStateOf(SchoolListingsState())

    private var searchJob: Job? = null

    init {
        getSchoolListings()
    }

    fun onEvent(event: SchoolListingsEvent) {
        when(event) {
            is SchoolListingsEvent.Refresh -> {
                getSchoolListings(fetchFromRemote = true)
            }
            is SchoolListingsEvent.OnSearchQueryChange -> {
                state = state.copy(searchQuery = event.query)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500L)
                    getSchoolListings()
                }
            }
        }
    }

    private fun getSchoolListings(
        query: String = state.searchQuery.lowercase(),
        fetchFromRemote: Boolean = false
    ) {
        viewModelScope.launch {
            repository
                .getSchoolListings(fetchFromRemote, query)
                .collect { result ->
                    when(result) {
                        is Resource.Success -> {
                            result.data?.let {
                                state = state.copy(
                                    schools = it

                                )
                            }
                        }
                        is Resource.Error -> Unit
                        is Resource.Loading -> {
                            state = state.copy(isLoading = result.isLoading)
                        }
                    }
                }
        }
    }
}