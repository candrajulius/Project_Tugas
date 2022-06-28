package com.candra.project_tugas.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.candra.project_tugas.databinding.FragmentSettingBinding
import com.candra.project_tugas.utils.Helper

class FragmentSetting: Fragment()
{
    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!
    private val dataPeople by lazy { Helper.loadPeople(requireActivity()) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        enterDataToActivity()
    }

    private fun enterDataToActivity(){
        binding.apply {
            with(dataPeople){
                edtPegawai.setText(pengguna)
                edtname.setText(name)
                edtEmail.setText(email)
                edtNmrHp.setText(nmrHp)
                edtPassword.setText(password)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}