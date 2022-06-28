package com.candra.project_tugas.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.candra.project_tugas.adapter.content_buy_adapter
import com.candra.project_tugas.databinding.FragmentTranctionBuyBinding
import com.candra.project_tugas.utils.Helper

class FragmentTransaksiPembelian: Fragment()
{

    private var _binding: FragmentTranctionBuyBinding? = null
    private val binding get() = _binding!!
    private val mainAdapter by lazy { content_buy_adapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTranctionBuyBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initContent()
        setAdapter()
    }

    private fun initContent(){
        mainAdapter.setData(Helper.loadDataTransaksiPembelian(requireActivity()))
    }

    private fun setAdapter(){
        binding.rvContainerBuy.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = mainAdapter
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}