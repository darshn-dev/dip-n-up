package dev.darshn.dipnup.data.csv

import com.opencsv.CSVReader
import dev.darshn.dipnup.domain.model.CompanyListing
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.InputStreamReader
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CompanyListParser @Inject constructor() : CsvParser<CompanyListing> {
    override suspend fun parse(stream: InputStream): List<CompanyListing> {
        val csvReader = CSVReader(InputStreamReader(stream))
        return withContext(Dispatchers.IO) {
            csvReader.readAll()
                .drop(1).mapNotNull {
                    CompanyListing(name = it[0], symbol = it[1], exchange = it[2])
                }
                .also {
                    csvReader.close()
                }
        }

    }

}