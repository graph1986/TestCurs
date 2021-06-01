package com.example.testcurs.model

import com.example.testcurs.model.repositories.CurrencyRepo
import javax.inject.Inject

class HttpCurrencyRepo @Inject constructor(private val apiClient: ApiClient) : CurrencyRepo {
    override fun fetchRecords(date1: String, date2: String, id: String) =
        apiClient.fetchRecords(date1, date2, id)
}