package com.apps.eduardomorales.gapsitest.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.apps.eduardomorales.gapsitest.Interfaces.GenericInterface
import com.apps.eduardomorales.gapsitest.R

class SearchHistoryAdapter(private val criteriaList: MutableList<String>, private val listener: GenericInterface) :
    RecyclerView.Adapter<SearchHistoryAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.criteria_data,parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return criteriaList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.criteria.text = criteriaList[position]

        holder.goBtn.setOnClickListener {
            listener.itemClicked(position)
        }
    }

    inner class ViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {
        internal val criteria: TextView
        internal val goBtn: Button


        init {
            criteria = view.findViewById(R.id.criteriaField)
            goBtn = view.findViewById(R.id.goBtn)
        }
    }

}