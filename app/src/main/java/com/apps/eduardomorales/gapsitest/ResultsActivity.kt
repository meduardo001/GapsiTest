package com.apps.eduardomorales.gapsitest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.apps.eduardomorales.gapsitest.Adapters.SearchResultsAdapter
import com.apps.eduardomorales.gapsitest.Interfaces.GenericInterface
import com.apps.eduardomorales.gapsitest.Managers.RequestManager
import com.apps.eduardomorales.gapsitest.Models.Products
import com.apps.eduardomorales.gapsitest.Tools.CustomLoader
import kotlinx.android.synthetic.main.activity_results.*

class ResultsActivity : AppCompatActivity() {

    private lateinit var loadingAnimation: CustomLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        loadingAnimation = CustomLoader(this)
        loadingAnimation.initLoader()

        val s = intent.getStringExtra("s")

        var mLayoutManager = LinearLayoutManager(this)
        itemsFounded!!.layoutManager = mLayoutManager

        val listener = object : GenericInterface {
            override fun requestError() {
                finish()
            }

            override fun itemClicked(position: Int) {

            }

            override fun productsRequestResult(products: MutableList<Products>) {
                totalItems.text = products.size.toString()
                itemsFounded.adapter = SearchResultsAdapter(products)
                loadingAnimation.closeLoader()
            }

        }

        RequestManager(this,listener,s)

    }
}
