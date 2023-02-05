package dev.darshn.dipnup.presentation.companylist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.darshn.dipnup.domain.repository.StockRepository
import dev.darshn.dipnup.util.Resource
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompanyListViewModel @Inject constructor(private val repository: StockRepository) :
    ViewModel() {
    var state by mutableStateOf(CompanyListState())
    var job: Job? = null

    init {
        getCompanyList()
    }

    fun onEvent(events: CompanyListingEvents) {
        when (events) {
            is CompanyListingEvents.OnSearchQueryChange -> {
                state = state.copy(searchQuery = events.query)
                job?.cancel()
                job = viewModelScope.launch {
                    delay(500L)
                    getCompanyList(state.searchQuery, true)
                }
                getCompanyList(fetchFromRemote = true)
            }
            is CompanyListingEvents.Refresh -> {
                getCompanyList(fetchFromRemote = true)
            }
        }
    }


    fun getCompanyList(
        query: String = state.searchQuery.toLowerCase(),
        fetchFromRemote: Boolean = false
    ) {
        viewModelScope.launch {
            repository.getCompanyList(query = query, fetchFromRemote = fetchFromRemote)
                .collect { result ->
                    when (result) {
                        is Resource.Error -> {
                            TODO()
                        }
                        is Resource.Loading -> {
                            state = state.copy(isLoading = result.isLoading)
                        }
                        is Resource.Success -> {
                            result.data?.let { list ->
                                state = state.copy(companyList = list)

                            }
                        }
                    }
                }
        }
    }

}