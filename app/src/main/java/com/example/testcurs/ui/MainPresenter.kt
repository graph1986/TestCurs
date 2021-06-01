package com.example.testcurs.ui

import android.content.Context
import android.content.SharedPreferences
import com.example.testcurs.model.repositories.CurrencyRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class MainPresenter @Inject constructor(
    private val repo: CurrencyRepo,
    private val context: Context
) : MainContract.Presenter {

    private var disposable: Disposable? = null

    private var view: MainContract.View? = null

    override fun onStart(view: MainContract.View) {
        this.view = view
        fetchRecords()
    }

    override fun onStop() {
        disposable?.dispose()
        this.view = null
    }

    fun fetchRecords() {
        val calendar = Calendar.getInstance()
        val date1 = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar.time)
        calendar.add(Calendar.MONTH, -1)
        val date2 = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar.time)
        disposable = repo.fetchRecords(date2, date1, DOLLAR_ID)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                response.records?.let { view?.showRecords(it) }
            }, { view?.showError() })
    }

    override fun saveValue(sPref: SharedPreferences, value: String) {
        val editor: SharedPreferences.Editor = sPref.edit()
        editor.putString(VALUE, value).apply()
    }

    override fun loadValue(sPref: SharedPreferences) {
        val value=sPref.getString(VALUE,"")
        value?.let { view?.showValue(it) }
    }

    private companion object {
        const val DOLLAR_ID = "R01235"
        const val VALUE = "Value"
    }

}