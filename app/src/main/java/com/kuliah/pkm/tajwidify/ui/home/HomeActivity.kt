package com.kuliah.pkm.tajwidify.ui.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.kuliah.pkm.tajwidify.R
import com.kuliah.pkm.tajwidify.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }

    private lateinit var navController: NavController
    private var isSearchMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Inisialisasi NavController
        val navHostController = supportFragmentManager.findFragmentById(R.id.fragmentHomeView) as NavHostFragment
        navController = navHostController.navController

        // Menghubungkan BottomNavigationView dengan NavController
        binding.bottomNavView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id) {
                R.id.homeFragment,
                R.id.doaFragment,
                R.id.profileFragment -> {
                    binding.bottomNavView.visibility = View.VISIBLE
                }
                else -> {
                    binding.bottomNavView.visibility = View.GONE
                }
            }
        }

        binding.bottomNavView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    navController.navigate(R.id.homeFragment)
                    true
                }

                R.id.doa -> {
                    navController.navigate(R.id.doaFragment)
                    true
                }

                R.id.profile -> {
                    navController.navigate(R.id.profileFragment)
                    true
                }

                else -> false
            }
        }
    }

    override fun onBackPressed() {
        // Jika menekan back pada HomeActivity, maka keluar dari aplikasi
        val currentDestinationId = navController.currentDestination?.id

        if (currentDestinationId == R.id.homeFragment ||
            currentDestinationId == R.id.doaFragment ||
            currentDestinationId == R.id.profileFragment)
        {
            finishAffinity()
        } else {
            super.onBackPressed()
        }
    }

}