package dev.darshn.dipnup.domain.repository

import dev.darshn.dipnup.domain.model.CompanyListing
import dev.darshn.dipnup.util.Resource
import kotlinx.coroutines.flow.Flow


interface StockRepository {

    suspend fun getCompanyList(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>>
}