package com.irfan.challenge7.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.irfan.challenge7.App
import com.irfan.challenge7.R
import com.irfan.challenge7.storage.PreferencesManager
import com.irfan.challenge7.utils.EdgeToEdgeUtils
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var storage: PreferencesManager

    @Inject lateinit var viewModelFactory: ViewModelFactory

    private val mainViewModel: MainViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        mainViewModel.isLoggedIn.observe(this) { isLoggedIn ->
            if (isLoggedIn) {
                navController.navigate(R.id.action_global_homeFragment)
            } else {
                navController.navigate(R.id.action_global_loginFragment)
            }
        }

        EdgeToEdgeUtils.applyEdgeToEdge(window)
    }
}