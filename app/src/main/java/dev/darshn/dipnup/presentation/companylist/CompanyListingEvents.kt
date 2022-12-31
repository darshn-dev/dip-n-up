package dev.darshn.dipnup.presentation.companylist

sealed class CompanyListingEvents{
    object Refresh : CompanyListingEvents()
    data class OnSearchQueryChange(val query:String) : CompanyListingEvents()

}