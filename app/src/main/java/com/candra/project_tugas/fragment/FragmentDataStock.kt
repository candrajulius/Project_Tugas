package com.candra.project_tugas.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.candra.project_tugas.adapter.ContentStockBarangAdapter
import com.candra.project_tugas.databinding.FragmentStockBinding
import com.candra.project_tugas.utils.Helper

class FragmentDataStock: Fragment()
{
    private var _binding : FragmentStockBinding? = null
    private val binding get() = _binding!!
    private val adapterMain by lazy { ContentStockBarangAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStockBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        setAdapter()
    }


    private fun setAdapter(){
        binding.rvContainerStock.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = adapterMain
        }
    }

    private fun initData(){
        adapterMain.setDataAll(Helper.loadDataStockBarang(requireActivity()))
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}