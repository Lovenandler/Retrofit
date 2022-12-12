package com.hello.retrofit2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyCountryAdapter(private val context: Context, private val countryList: MutableList<Country>): RecyclerView.Adapter<MyCountryAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.layout_country_info, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.txt_name.text = countryList[position].name
        holder.txt_capital.text = countryList[position].capital
        holder.txt_region.text = countryList[position].region
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txt_name : TextView
        var txt_region : TextView
        var txt_capital : TextView

        init {
            txt_name = itemView.findViewById(R.id.txt_name)
            txt_region = itemView.findViewById(R.id.txt_region)
            txt_capital = itemView.findViewById(R.id.txt_capital)
        }
    }
}