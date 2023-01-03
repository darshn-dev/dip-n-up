package dev.darshn.dipnup.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.darshn.dipnup.data.csv.CompanyListParser
import dev.darshn.dipnup.data.csv.CsvParser
import dev.darshn.dipnup.data.remote.StockApi
import dev.darshn.dipnup.data.repository.StockRepositoryImpl
import dev.darshn.dipnup.domain.model.CompanyListing
import dev.darshn.dipnup.domain.repository.StockRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {


    // why this has CompanyListParser as parameter
    @Binds
    @Singleton
    abstract fun bindCompanyListParser(companyListParser: CompanyListParser) : CsvParser<CompanyListing>

    @Binds
    @Singleton
    abstract fun bindStockRepository(stockRepositoryImpl: StockRepositoryImpl) : StockRepository
}