package com.example.testcurs.model.repositories

import com.example.testcurs.model.entities.ValCurs
import io.reactivex.Single

interface CurrencyRepo{

    fun fetchRecords(date1:String, date2:String, id:String): Single<ValCurs>
}