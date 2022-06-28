package com.candra.project_tugas.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.candra.project_tugas.data.Penjualan
import com.candra.project_tugas.databinding.FragmentBerandaBinding
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate

class BerandaFragment: Fragment() {

    private  val listEntry : ArrayList<BarEntry> = arrayListOf()
    private  var  penjualanList: ArrayList<Penjualan> = arrayListOf()

    private var _binding: FragmentBerandaBinding? = null
    private val binding get() = _binding!!
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBerandaBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBarChart()
    }

    private fun setBarChart(){
       penjualanList = getListData()

        initBarChart()

        for (i in penjualanList.indices){
            val penjualan = penjualanList[i]
            listEntry.add(BarEntry(i.toFloat(),penjualan.total.toFloat()))
        }

        val barDataSet = BarDataSet(listEntry,"Total penjualan")
        barDataSet.setColors(*ColorTemplate.JOYFUL_COLORS)

        val data = BarData(barDataSet)
        binding.barChart.data = data

    }

    private fun initBarChart(){
        binding.apply {
            barChart.axisLeft.setDrawGridLines(false)
            val xAxis: XAxis = barChart.xAxis
            xAxis.setDrawGridLines(false)
            xAxis.setDrawAxisLine(false)

            //remove right y-axis
            barChart.axisRight.isEnabled = false

            //remove legend
            barChart.legend.isEnabled = false


            //remove description label
            barChart.description.isEnabled = true
            barChart.description.text = "Laporan Penjualan"


            //add animation
            barChart.animateY(3000)

            // to draw label on xAxis
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.valueFormatter = myAxisFormatter
            xAxis.setDrawLabels(true)
            xAxis.granularity = 1f
        }

    }

    private val myAxisFormatter = object: IndexAxisValueFormatter(){
        override fun getAxisLabel(value: Float, axis: AxisBase?): String {
            val index = value.toInt()
            return if (index < penjualanList.size){
                penjualanList[index].hari
            }else{
                ""
            }
        }
    }

    private fun getListData(): ArrayList<Penjualan>{
        penjualanList.add(Penjualan(35,"Day 1"))
        penjualanList.add(Penjualan(50,"Day 2"))
        penjualanList.add(Penjualan(60,"Day 3"))
        penjualanList.add(Penjualan(80,"Day 4"))
        penjualanList.add(Penjualan(93,"Day 5"))
        penjualanList.add(Penjualan(70,"Day 6"))
        penjualanList.add(Penjualan(50,"Day 7"))
        penjualanList.add(Penjualan(30,"Day 8"))
        penjualanList.add(Penjualan(40,"Day 9"))

        return penjualanList
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}