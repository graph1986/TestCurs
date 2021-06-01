package com.example.testcurs.ui

import android.content.Context
import android.content.SharedPreferences
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.testcurs.R
import com.example.testcurs.databinding.ActivityMainBinding
import com.example.testcurs.model.entities.Record
import com.example.testcurs.ui.adapters.MainAdapter
import dagger.android.AndroidInjection
import java.text.DecimalFormat
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainContract.View {

    @Inject
    lateinit var presenter: MainContract.Presenter

    private lateinit var binding: ActivityMainBinding

    private lateinit var sPref:SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        DividerItemDecoration(this, RecyclerView.VERTICAL).run {
            binding.recycleCurs.addItemDecoration(this)
        }
        sPref=getPreferences(Context.MODE_PRIVATE)
        binding.run {
            txtTitleDate.setText(R.string.date)
            txtTitleCurs.setText(R.string.curs)
            txtNameValute.setText(R.string.currency_dollar_usa)
            btn.setText(R.string.change_value)
            btn.setOnClickListener(View.OnClickListener {
                if (!edtValue.isVisible) {
                    txtValue.visibility=View.GONE
                    edtValue.visibility=View.VISIBLE
                    btn.setText(R.string.save_value)
                } else {
                    presenter.saveValue(sPref,edtValue.text.toString())
                    txtValue.visibility=View.VISIBLE
                    txtValue.setText(edtValue.text)
                    edtValue.visibility = View.GONE
                    btn.setText(R.string.change_value)
                }
            })
        }
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart(this)
        presenter.loadValue(sPref)
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }

    override fun showValue(value:String) {
        binding.txtValue.setText(value)
    }

    override fun showError() {
        Toast.makeText(this, R.string.connection_error, Toast.LENGTH_SHORT).show()
    }

    override fun showRecords(curs: List<Record>) {
        binding.recycleCurs.adapter = MainAdapter(curs)
    }

}