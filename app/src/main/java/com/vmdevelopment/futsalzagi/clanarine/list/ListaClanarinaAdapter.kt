package com.vmdevelopment.futsalzagi.clanarine.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.vmdevelopment.futsalzagi.R
import com.vmdevelopment.futsalzagi.data.models.Clanarina

class ListaClanarinaAdapter: RecyclerView.Adapter<ListaClanarinaAdapter.MyViewHolder>() {

    var dataList = emptyList<Clanarina>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.clanarina_row_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.platisaTextView).text = dataList[position].clan
        holder.itemView.findViewById<TextView>(R.id.iznosUplateTextView).text = "(${dataList[position].mjesec}) " + dataList[position].iznos.toString() + " kn"

        holder.itemView.findViewById<ConstraintLayout>(R.id.clanarina_row_background).setOnClickListener {
            val action = ListaClanarinaFragmentDirections.actionListaClanarinaFragmentToUrediClanarinuFragment(dataList[position])
            holder.itemView.findNavController().navigate(action)
        }

        if (dataList[position].placeno && dataList[position].iznos < 200) {
            holder.itemView.findViewById<CardView>(R.id.indikatorUplate).setCardBackgroundColor(ContextCompat.getColor(
                holder.itemView.context,
                R.color.yellow
            ))
        } else if (dataList[position].placeno && dataList[position].iznos >= 200) {
            holder.itemView.findViewById<CardView>(R.id.indikatorUplate).setCardBackgroundColor(ContextCompat.getColor(
                holder.itemView.context,
                R.color.green
            ))
        } else if (!dataList[position].placeno) {
            holder.itemView.findViewById<CardView>(R.id.indikatorUplate).setCardBackgroundColor(ContextCompat.getColor(
                holder.itemView.context,
                R.color.red
            ))
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setData(clanarina: List<Clanarina>) {
        this.dataList = clanarina
        notifyDataSetChanged()
    }
}