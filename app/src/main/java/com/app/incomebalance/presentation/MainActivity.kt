package com.app.incomebalance.presentation


import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.appcompat.app.AppCompatDelegate.setDefaultNightMode
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.app.incomebalance.R
import com.app.incomebalance.common.appComponent
import com.app.incomebalance.databinding.ActivityMainBinding
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override val navigator: Navigator = object : AppNavigator(this, R.id.fragment_container) {

        override fun applyCommands(commands: Array<out Command>) {
            super.applyCommands(commands)
            supportFragmentManager.executePendingTransactions()
        }

        override fun setupFragmentTransaction(
            screen: FragmentScreen,
            fragmentTransaction: FragmentTransaction,
            currentFragment: Fragment?,
            nextFragment: Fragment
        ) {
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setDefaultNightMode(MODE_NIGHT_YES)
        appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//
//        supportFragmentManager.beginTransaction()
//            .add(binding.fragmentContainer.id, PagerFragment.newInstance()).commit()
    }
}