package com.vmdevelopment.futsalzagi.clanovi.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.vmdevelopment.futsalzagi.R
import com.vmdevelopment.futsalzagi.data.models.Clan

class ListAdapter:RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    var dataList = emptyList<Clan>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.ime_tv).text = "${dataList[position].ime} ${dataList[position].prezime}"
        holder.itemView.findViewById<TextView>(R.id.datum_rodjenja_tv).text = dataList[position].datumRodjenja

        holder.itemView.findViewById<ConstraintLayout>(R.id.row_background).setOnClickListener {
            val action = ListaClanovaFragmentDirections.actionListaClanovaFragmentToUrediClanaFragment(dataList[position])
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setData(clan: List<Clan>) {
        this.dataList = clan
        notifyDataSetChanged()
    }
}