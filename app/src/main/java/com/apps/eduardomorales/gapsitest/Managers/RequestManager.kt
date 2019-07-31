package com.apps.eduardomorales.gapsitest.Managers

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.apps.eduardomorales.gapsitest.Interfaces.GenericInterface
import com.apps.eduardomorales.gapsitest.Models.Products
import com.apps.eduardomorales.gapsitest.R
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class RequestManager constructor(context: Context, listener: GenericInterface, val s: String)  {

    private val URL_STORE_REQUEST = "https://www.liverpool.com.mx/tienda/?s=$s&d3106047a194921c01969dfdec083925=json"

    init {
        var products = mutableListOf<Products>()

        val request = object : StringRequest(
            Method.GET,URL_STORE_REQUEST,
            Response.Listener {
                try {

                    val jsonObj = JSONObject(it.toString()).getJSONArray("contents").getJSONObject(0).getJSONArray("mainContent").getJSONObject(3).getJSONArray("contents").getJSONObject(0)
                    val jsonArray: JSONArray = jsonObj.getJSONArray("records")

                    for (i in 0 until jsonArray.length()){
                        val product = jsonArray[i] as JSONObject


                        products.add(
                            Products(product.getJSONArray("productDisplayName")[0].toString(),
                                product.getJSONArray("thumbnailImage")[0].toString(), product.getJSONArray("sellerName")[0].toString(),
                                product.getJSONArray("productPrice")[0].toString().toFloat())
                        )


                    }


                    listener.productsRequestResult(products)


                }catch (e: JSONException){
                    Toast.makeText(context,context.getString(R.string.error_request),Toast.LENGTH_LONG).show()
                    listener.requestError()
                }
            }, Response.ErrorListener {
                Log.e("Error",it.message.toString())
            }){}

        val requestQ = Volley.newRequestQueue(context)
        requestQ.add(request)
    }
}
