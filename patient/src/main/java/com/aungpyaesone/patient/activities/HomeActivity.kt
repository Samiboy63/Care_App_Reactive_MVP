package com.aungpyaesone.patient.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.aungpyaesone.patient.R
import com.aungpyaesone.patient.fragments.AccountFragment
import com.aungpyaesone.patient.fragments.ConsultationFragment
import com.aungpyaesone.patient.fragments.HomeFragment
import com.aungpyaesone.patient.utils.SessionManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.aungpyaesone.shared.activites.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : BaseActivity() {

    private var mUserId: String? = null
    private var mData: String? = null

    companion object {
        fun newInstance(context: Context) = Intent(context, HomeActivity::class.java).apply {

        }
    }


    private val homeFragment = HomeFragment.newInstance("", "")
    private val consultationFragment = ConsultationFragment.newInstance("", "")
    private val accountFragment = AccountFragment.newInstance("", "")
    private val fragmentManager = supportFragmentManager
    private var activeFragment: Fragment = HomeFragment.newInstance("", "")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupFragmentManager()
        callFragment(homeFragment)
        setUpBottomNav()
    }


    private fun setupFragmentManager() {
        fragmentManager.beginTransaction().apply {
            add(R.id.container, homeFragment, getString(R.string.home_fragment)).hide(homeFragment)
            add(
                R.id.container,
                consultationFragment,
                getString(R.string.consultation_fragment)
            ).hide(consultationFragment)
            add(R.id.container, accountFragment, getString(R.string.account_fragment)).hide(
                accountFragment
            )
        }.commit()
    }

    private fun setUpBottomNav() {
        bottomNav.setOnNavigationItemSelectedListener(object :
            BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when (item.itemId) {
                    R.id.home -> {
                        callFragment(homeFragment)
                        return true
                    }
                    R.id.message -> {
                        callFragment(consultationFragment)
                        return true
                    }
                    R.id.account -> {
                        callFragment(accountFragment)
                        return true
                    }
                }
                return false
            }
        })

    }

    override fun showLoading() {
        showProgressDialog()
    }

    override fun hideLoading() {
        showProgressDialog()
    }

    fun callFragment(fragment: Fragment) {
        fragmentManager.beginTransaction().hide(activeFragment).show(fragment).commit()
        activeFragment = fragment
    }

}