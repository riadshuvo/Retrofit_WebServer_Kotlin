package com.example.retrofit_restfulapi_globofly.viewmodel.view.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit_restfulapi_globofly.R
import com.example.retrofit_restfulapi_globofly.viewmodel.view.activities.DestinationDetailActivity
import com.example.retrofit_restfulapi_globofly.networkHelper.models.Destination

class DestinationAdapter(val context: Context) : RecyclerView.Adapter<DestinationAdapter.ViewHolder>() {

    private var destinationList: List<Destination> = ArrayList()

    fun destinationList(list: List<Destination>) {
        this.destinationList = list
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(
            view
        )
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txvDestination.text = destinationList[position].city

        holder.itemView.setOnClickListener { v ->
            val context = v.context

            val intent = Intent(context, DestinationDetailActivity::class.java)
            intent.putExtra(DestinationDetailActivity.ARG_ITEM_ID, destinationList[position].id)

            context.startActivity(intent)

        }

    }

    override fun getItemCount(): Int {
        return destinationList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var txvDestination: TextView = itemView.findViewById(R.id.txv_destination)

        override fun toString(): String {
            return """${super.toString()} '${txvDestination.text}'"""
        }

    }


}