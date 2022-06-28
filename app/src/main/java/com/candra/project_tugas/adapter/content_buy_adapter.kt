package com.candra.project_tugas.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.candra.project_tugas.data.data_pembelian
import com.candra.project_tugas.databinding.ContentBuyItemBinding

class content_buy_adapter(): RecyclerView.Adapter<content_buy_adapter.ViewHolder>() {

    private var listDataContent = ArrayList<data_pembelian>()

    fun setData(newListData: List<data_pembelian>){
        if (newListData == null) return
        listDataContent.clear()
        listDataContent.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): content_buy_adapter.ViewHolder {
        return ViewHolder(
            ContentBuyItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: content_buy_adapter.ViewHolder, position: Int) {
       val content = listDataContent[position]
       holder.bind(content,holder.itemView.context)
    }

    override fun getItemCount(): Int = listDataContent.size

    class ViewHolder(private val binding: ContentBuyItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: data_pembelian,context: Context){
            with(binding){
                tglContent.text = data.tanggal
                if (data.status.equals("jatuh tempo",true)){
                    contentStatus.setTextColor(ContextCompat.getColor(context,android.R.color.holo_orange_light))
                }else if (data.status.equals("lunas dibayar",true)){
                    contentStatus.setTextColor(ContextCompat.getColor(context,android.R.color.holo_green_light))
                }else if (data.status.equals("belum dibayar",true)){
                    contentStatus.setTextColor(ContextCompat.getColor(context,android.R.color.holo_red_light))
                }

                contentStatus.text = data.status
                contentSuplier.text = data.suplier
                contentTotal.text = data.total
            }
        }
    }
}