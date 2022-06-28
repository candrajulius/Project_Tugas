package com.candra.project_tugas.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.candra.project_tugas.data.data_penjualan
import com.candra.project_tugas.databinding.ContentSellItemBinding
import com.candra.project_tugas.databinding.FragmentTransactionSellBinding

class ContentSellAdapter: RecyclerView.Adapter<ContentSellAdapter.ViewHolder>()
{

    private val listArray: ArrayList<data_penjualan> = arrayListOf()

    class ViewHolder(private val binding: ContentSellItemBinding): RecyclerView.ViewHolder(
        binding.root
    ){
       fun bind(dataPenjualan: data_penjualan,context: Context){
           with(binding){
               with(dataPenjualan){
                   tglContent.text = tanggal
                   contentSuplier.text = nama_pelanggan
                   contentStatus.setTextColor(ContextCompat.getColor(context,android.R.color.holo_green_light))
                   contentStatus.text = status
                   contentTotal.text = no_invoice
                   contentHasil.text = hasil
               }
           }
       }
    }

    fun setDataAll(newListData: List<data_penjualan>){
        if (newListData == null )return
        listArray.clear()
        listArray.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContentSellAdapter.ViewHolder = ViewHolder(
        ContentSellItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )

    override fun onBindViewHolder(holder: ContentSellAdapter.ViewHolder, position: Int) {
        holder.bind(listArray[position],holder.itemView.context)
    }

    override fun getItemCount(): Int = listArray.size
}