package com.example.testcurs.ui

import android.content.SharedPreferences
import com.example.testcurs.model.entities.Record

interface MainContract {

    interface View {

        fun showValue(value:String)

        fun showError()

        fun showRecords(curs:List<Record>)

    }

    interface Presenter {

        fun saveValue(sPref:SharedPreferences,value:String)

        fun loadValue(sPref:SharedPreferences)

        fun onStart(view: View)

        fun onStop()

    }

}