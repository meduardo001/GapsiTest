package com.apps.eduardomorales.gapsitest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.apps.eduardomorales.gapsitest.Adapters.SearchHistoryAdapter
import com.apps.eduardomorales.gapsitest.DB.DBHandler
import com.apps.eduardomorales.gapsitest.Interfaces.GenericInterface
import com.apps.eduardomorales.gapsitest.Models.Products
import com.apps.eduardomorales.gapsitest.Tools.CustomLoader
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var dbHelper: DBHandler
    private var criteriaList: MutableList<String> = ArrayList()

    private lateinit var listener: GenericInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        dbHelper = DBHandler(this)

        initData()
    }

    fun saveAndSearch(v: View){
        val toSearch: String = criteriaInput.text.toString()

        if(dbHelper.saveSearchCriteria(toSearch))
            search(toSearch)
        else
            Toast.makeText(this,getString(R.string.error_saving_data),Toast.LENGTH_LONG).show()
    }

    private fun initData() {
        criteriaList.addAll(dbHelper.getAllSearchCriteria())

        if(criteriaList.size>0){
            var mLayoutManager = LinearLayoutManager(this)
            criteriaViewList!!.layoutManager = mLayoutManager

            listener = object : GenericInterface {
                override fun requestError() {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun productsRequestResult(products: MutableList<Products>) {
                }

                override fun itemClicked(position: Int) {
                    search(criteriaList[position])
                }
            }

            criteriaViewList.adapter = SearchHistoryAdapter(criteriaList,listener)

        }


    }

    private fun search(s: String) {
        val intent = Intent(this, ResultsActivity::class.java)
        intent.putExtra("s", s)
        startActivity(intent)

    }
}
