package dev.darshn.dipnup.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [CompanyListEntity::class],
    version = 1
)
abstract class StockDatabase : RoomDatabase() {
    abstract val companyListDao: CompanyListDao
}