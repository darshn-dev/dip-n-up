package dev.darshn.dipnup.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CompanyListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCompanyListEntity(companyListEntity: List<CompanyListEntity>)

    @Query("DELETE from CompanyListEntity")
    suspend fun clearCompanyListings()

    @Query("SELECT * from companylistentity where LOWER(name) like '%' || LOWER(:query) || '%' OR UPPER(:query) == symbol")
    suspend fun searchCompanyListing(query: String): List<CompanyListEntity>

}