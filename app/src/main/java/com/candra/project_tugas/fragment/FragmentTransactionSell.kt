package com.candra.project_tugas.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.candra.project_tugas.adapter.ContentSellAdapter
import com.candra.project_tugas.databinding.FragmentTransactionSellBinding
import com.candra.project_tugas.utils.Helper

class FragmentTransactionSell: Fragment()
{
    private var _binding: FragmentTransactionSellBinding? = null
    private val adapterMain by lazy { ContentSellAdapter() }

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTransactionSellBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDataToAttachRecyclerview()
        setAdapter()
    }

    private fun setAdapter(){
        binding.rvContainerSellItem.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = adapterMain
        }
    }

    private fun initDataToAttachRecyclerview(){
        adapterMain.setDataAll(Helper.loadDataTransaksiPenjualan(requireActivity()))
    }
}