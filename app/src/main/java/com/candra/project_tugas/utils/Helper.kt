package com.candra.project_tugas.utils

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.candra.project_tugas.R
import com.candra.project_tugas.data.*
import org.json.JSONObject
import java.io.IOException

object Helper {


    private var tanggal = ""
    private var suplier = ""
    private var total = ""
    private var status = ""
    private var nama_pelanggan = ""
    private var no_invoice = ""
   private var namaProduk = ""
    private var category = ""
    private var stockBarang = ""

    private fun parsingFileToString(context: Context,data: Int): String{
        val jsonString: String
        try {
            jsonString = context.resources.openRawResource(data).bufferedReader()
                .use { it.readText() }
        }catch (ioException: IOException){
            val message = ioException.printStackTrace()
            return message.toString()
        }
        return jsonString
    }

    fun loadDataTransaksiPembelian(context: Context): List<data_pembelian>{
        val listData = ArrayList<data_pembelian>()
        val listArray = JSONObject(parsingFileToString(context,R.raw.transkasi_pembelian)).getJSONArray("data")
        listArray.let {
            for (i in 0 until it.length()){
                it.getJSONObject(i).apply {
                    tanggal = getString("tanggal")
                    suplier = getString("suplier")
                    total = getString("total")
                    status = getString("status")
                }
                val dataBeliObjek = data_pembelian(
                    tanggal = tanggal,
                    suplier = suplier,
                    status = status,
                    total = total
                )
                listData.add(dataBeliObjek)
            }
        }
        return listData
    }

    fun loadDataTransaksiPenjualan(context: Context): List<data_penjualan>{
        val listDataPenjualan = ArrayList<data_penjualan>()
        JSONObject(parsingFileToString(context,R.raw.transaksi_penjualan)).getJSONArray("data")
            .apply {
                for (i in 0 until length()){
                    getJSONObject(i).apply {
                        tanggal = getString("tanggal")
                        nama_pelanggan = getString("nama_pelanggan")
                        no_invoice = getString("no_invoice")
                        total = getString("hasil")
                        status = getString("status")
                    }
                    val modelDataPenjualan = data_penjualan(
                        tanggal = tanggal,
                        nama_pelanggan = nama_pelanggan,
                        no_invoice = no_invoice,
                        hasil = total,
                        status = status
                    )
                    listDataPenjualan.add(modelDataPenjualan)
                }
            }
        return listDataPenjualan
    }

    fun loadDataStockBarang(context: Context): List<DataStock>{
        val listData = ArrayList<DataStock>()
       JSONObject(parsingFileToString(context,R.raw.transkasi_pembelian))
            .getJSONArray("data_stock").apply {
                for (i in 0 until length()){
                    getJSONObject(i).apply {
                        namaProduk = getString("nama")
                        category = getString("kategori")
                        stockBarang = getString("stock")
                    }
                    val objectDataStock = DataStock(
                        namaProduk = namaProduk,
                        category = category,
                        stockBarang = stockBarang
                    )
                    listData.add(objectDataStock)
                }
            }
        return listData
    }

    fun loadDataBarang(context: Context): List<DataBarang>{
        val listData = ArrayList<DataBarang>()
        JSONObject(parsingFileToString(context,R.raw.transaksi_penjualan))
            .getJSONArray("data_barang").apply {
                for (i in 0 until length()){
                    getJSONObject(i).apply {
                        namaProduk = getString("id")
                        category = getString("nama")
                        stockBarang = getString("stok")
                    }
                    val objectDataStock = DataBarang(
                        id = namaProduk,
                        nama = category,
                        stok = stockBarang
                    )
                    listData.add(objectDataStock)
                }
            }
        return listData
    }


    fun loadPeople(context: Context): People{
        val people: People
        JSONObject(parsingFileToString(context,R.raw.people)).getJSONObject("people").apply {
            people = People(
                pengguna = getString("pengguna"),
                name = getString("name"),
                email = getString("email"),
                password = getString("password"),
                nmrHp = getString("nmrHp")
            )
        }
        return people
    }

    fun setMargins(view: View, left: Int) {
        if (view.getLayoutParams() is ViewGroup.MarginLayoutParams) {
            val p = view.getLayoutParams() as ViewGroup.MarginLayoutParams
            p.marginStart = left
            view.requestLayout()
        }
    }
}