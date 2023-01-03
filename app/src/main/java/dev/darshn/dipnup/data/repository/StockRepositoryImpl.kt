package dev.darshn.dipnup.data.repository

import dev.darshn.dipnup.data.csv.CsvParser
import dev.darshn.dipnup.data.local.StockDatabase
import dev.darshn.dipnup.data.mapper.toCompanyListEntity
import dev.darshn.dipnup.data.mapper.toCompanyListModel
import dev.darshn.dipnup.data.remote.StockApi
import dev.darshn.dipnup.domain.model.CompanyListing
import dev.darshn.dipnup.domain.repository.StockRepository
import dev.darshn.dipnup.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepositoryImpl @Inject constructor(
    private val api: StockApi,
    private val stockDatabase: StockDatabase,
    private val listParser: CsvParser<CompanyListing>
) :
    StockRepository {
    val dao = stockDatabase.companyListDao
    override suspend fun getCompanyList(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>> {
        return flow {
            emit(Resource.Loading(true))
            val companyList = dao.searchCompanyListing(query)
            emit(Resource.Success(companyList.map { it.toCompanyListModel() }))

            val isDbEmpty = companyList.isEmpty() && query.isBlank()
            val shouldLoadFromCache = !isDbEmpty && !fetchFromRemote
            if (shouldLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }


            var remoteList = try {
                val response = api.getCompanyList()
                listParser.parse(response.byteStream())
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Something went wrong"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Something went wrong"))
                null
            }

            remoteList?.let { listing ->
                dao.clearCompanyListings()
                dao.insertCompanyListEntity(listing.map { it -> it.toCompanyListEntity() })
                emit(Resource.Success(dao.searchCompanyListing("").map { it.toCompanyListModel() }))
                emit(Resource.Loading(false))
            }


        }
    }

}