package com.example.my_travel_guide.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.my_travel_guide.Entity.Travel
import com.example.my_travel_guide.R

class TravelListAdapter( val travelList: MutableList<Travel>): RecyclerView.Adapter<TravelListAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_card_travel, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.titleCard.text = travelList[position].name
    }

    override fun getItemCount() = travelList.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleCard: TextView = view.findViewById(R.id.title_card)
    }

}