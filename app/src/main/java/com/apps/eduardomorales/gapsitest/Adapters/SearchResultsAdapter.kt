package com.apps.eduardomorales.gapsitest.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.apps.eduardomorales.gapsitest.Models.Products
import com.apps.eduardomorales.gapsitest.R
import com.squareup.picasso.Picasso

class SearchResultsAdapter(private val results: MutableList<Products>):
    RecyclerView.Adapter<SearchResultsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultsAdapter.ViewHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.search_results,parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return results.size
    }

    override fun onBindViewHolder(holder: SearchResultsAdapter.ViewHolder, position: Int) {
        holder.productName.text = results[position].name
        holder.price.text = "$${results[position].price}"
        holder.location.text = results[position].location
        Picasso.get().load(results[position].thumbnail).fit().into(holder.thumbnail)
    }

    inner class ViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {
        internal val productName: TextView
        internal val thumbnail: ImageView
        internal val price: TextView
        internal val location: TextView


        init {
            productName = view.findViewById(R.id.productName)
            thumbnail = view.findViewById(R.id.thumbnail)
            price = view.findViewById(R.id.price)
            location = view.findViewById(R.id.location)

        }
    }
}