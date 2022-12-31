package dev.darshn.dipnup.presentation.companylist

import dev.darshn.dipnup.domain.model.CompanyListing

data class CompanyListState(
    val companyList: List<CompanyListing> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery:String =""
)
