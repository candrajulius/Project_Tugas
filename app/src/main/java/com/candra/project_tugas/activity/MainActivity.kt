package com.candra.project_tugas.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import com.candra.project_tugas.R
import com.candra.project_tugas.databinding.ActivityMainBinding
import com.candra.project_tugas.fragment.*
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarMain.toolbar)

        binding.apply {
            val toggle = ActionBarDrawerToggle(
                this@MainActivity,
                drawerLayout,
                appBarMain.toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
            )
            drawerLayout.addDrawerListener(toggle)
            toggle.syncState()

            navView.setNavigationItemSelectedListener(this@MainActivity)

            if (savedInstanceState == null){
                changeFragment(fragment = BerandaFragment())
                supportActionBar?.title = getString(R.string.beranda)
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment: Fragment? = null
        var title = getString(R.string.app_name)
        when(item.itemId){
            R.id.nav_beranda -> {
                fragment = BerandaFragment()
                title = getString(R.string.beranda)
            }
            R.id.nav_transaksi_pembelian -> {
                fragment = FragmentTransaksiPembelian()
                title = getString(R.string.transaksi_pembelian)
            }
            R.id.nav_transaksi_penjualan -> {
                fragment = FragmentTransactionSell()
                title = getString(R.string.transaksi_penjualan)
            }
            R.id.data_barang -> {
                fragment = FragmentDataBarang()
                title = getString(R.string.data_barang)
            }
            R.id.data_penjualan -> {
                title = getString(R.string.data_penjualan)
                fragment = FragmentTransactionSell()
            }
            R.id.data_stock -> {
                fragment = FragmentDataStock()
                title = getString(R.string.data_stok_barang)
            }
            R.id.setting -> {
                fragment = FragmentSetting()
                title = getString(R.string.pengaturan)
            }
            R.id.about -> {
                fragment = FragmentAbout()
                title = getString(R.string.tentang)
            }
            R.id.log_out -> {
                startActivity(Intent(this@MainActivity,Login::class.java))
                    .also { finish() }
            }
        }
        fragment?.let {
            changeFragment(it)
        }
        supportActionBar?.title = title
        return true
    }

    private fun changeFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment,fragment)
            .commit()
    }
}