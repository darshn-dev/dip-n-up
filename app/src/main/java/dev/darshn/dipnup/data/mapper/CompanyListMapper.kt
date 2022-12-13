package dev.darshn.dipnup.data.mapper

import dev.darshn.dipnup.data.local.CompanyListEntity
import dev.darshn.dipnup.domain.model.CompanyListing

fun CompanyListEntity.toCompanyListModel(): CompanyListing{
    return CompanyListing(name = name, symbol= symbol, exchange = exchange)
}

fun CompanyListing.toCompanyListEntity():CompanyListEntity{
    return CompanyListEntity(name, symbol, exchange)
}