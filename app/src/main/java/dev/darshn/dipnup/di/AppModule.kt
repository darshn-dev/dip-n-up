package dev.darshn.dipnup.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.darshn.dipnup.DipnupApplication
import dev.darshn.dipnup.data.local.StockDatabase
import dev.darshn.dipnup.data.remote.StockApi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideStockApi(): StockApi {
        return Retrofit.Builder().baseUrl(StockApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create()).build().create()
    }

    @Provides
    @Singleton
    fun provideStockDb(application: Application): StockDatabase {
        return Room.databaseBuilder(application, StockDatabase::class.java, "stock_db").build()
    }
}