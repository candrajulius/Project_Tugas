package com.candra.project_tugas.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import androidx.recyclerview.widget.RecyclerView
import com.candra.project_tugas.data.DataBarang
import com.candra.project_tugas.databinding.ContentStockBinding
import com.candra.project_tugas.utils.Helper

class ContentDataBarangAdapter: RecyclerView.Adapter<ContentDataBarangAdapter.ViewHolder>()
{

    private val listDataBarang: ArrayList<DataBarang> = arrayListOf()

    fun setDataToActivity(newData: List<DataBarang>){
        listDataBarang.clear()
        listDataBarang.addAll(newData)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ContentStockBinding): RecyclerView.ViewHolder(binding.root)
    {
        @SuppressLint("SetTextI18n")
        fun bindData(data: DataBarang){
            with(binding){
                with(data){
                    Helper.setMargins(containerCategory,35)
                    Helper.setMargins(containerNameProduk,15)
                    Helper.setMargins(containerStockBarang,15)
                    containerNameProduk.text = "ID"
                    containerCategory.text = "Nama"
                    containerStockBarang.text = "Stock"
                    contentProduk.text = id
                    contentStockBarang.text = stok
                    contentCategory.text = nama
                }
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContentDataBarangAdapter.ViewHolder {
       return ViewHolder(
           ContentStockBinding.inflate(LayoutInflater.from(parent.context),parent,false)
       )
    }

    override fun onBindViewHolder(holder: ContentDataBarangAdapter.ViewHolder, position: Int) {
       holder.bindData(listDataBarang[position])
    }

    override fun getItemCount(): Int = listDataBarang.size


}