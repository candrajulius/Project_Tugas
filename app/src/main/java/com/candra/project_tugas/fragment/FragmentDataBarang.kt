package com.candra.project_tugas.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.candra.project_tugas.adapter.ContentDataBarangAdapter
import com.candra.project_tugas.adapter.ContentStockBarangAdapter
import com.candra.project_tugas.databinding.FragmentDataBarangBinding
import com.candra.project_tugas.utils.Helper

class FragmentDataBarang: Fragment(){

    private var _binding : FragmentDataBarangBinding? = null
    private val binding get() = _binding!!
    private val mainAdapter by lazy { ContentDataBarangAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDataBarangBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDataAction()
        setAdapter()
    }

    private fun setAdapter(){
        binding.rvContainerDataBarang.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = mainAdapter
        }
    }

    private fun initDataAction(){
        mainAdapter.setDataToActivity(Helper.loadDataBarang(requireActivity()))
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}