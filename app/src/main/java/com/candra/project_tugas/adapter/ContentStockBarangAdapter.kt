package com.candra.project_tugas.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.candra.project_tugas.data.DataBarang
import com.candra.project_tugas.data.DataStock
import com.candra.project_tugas.databinding.ContentStockBinding

class ContentStockBarangAdapter : RecyclerView.Adapter<ContentStockBarangAdapter.ViewHolder>()
{

    private val listDataArray: ArrayList<DataStock> = arrayListOf()
    private val listDataArray2: ArrayList<DataBarang> = arrayListOf()

    fun setDataAll(data: List<DataStock>){
        listDataArray.clear()
        listDataArray.addAll(data)
        notifyDataSetChanged()

    }

    class ViewHolder(private val binding: ContentStockBinding):
    RecyclerView.ViewHolder(binding.root)
    {

        fun bind(data: DataStock){
            with(binding){
                with(data){
                    contentProduk.text = namaProduk
                    contentCategory.text = category
                    contentStockBarang.text = stockBarang
                    btnDelete.setOnClickListener {
                        Toast.makeText(itemView.context,"Anda menghapus item $namaProduk",
                            Toast.LENGTH_SHORT)
                            .show()
                    }
                    btnEdit.setOnClickListener {
                        Toast.makeText(itemView.context,"Anda edit item $namaProduk",
                        Toast.LENGTH_SHORT).show()
                    }
                        rootContainer.setOnClickListener {
                            Toast.makeText(itemView.context,"Anda memilih item $namaProduk",
                                Toast.LENGTH_SHORT).show()
                    }

                }
            }
        }

    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContentStockBarangAdapter.ViewHolder {
        return ViewHolder(
            ContentStockBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: ContentStockBarangAdapter.ViewHolder, position: Int) {
        holder.bind(listDataArray[position])
    }

    override fun getItemCount(): Int = listDataArray.size

}