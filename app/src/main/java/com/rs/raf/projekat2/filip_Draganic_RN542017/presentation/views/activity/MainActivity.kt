package com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.views.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.views.viewpager.UpperPagerAdapter
import com.rsrafprojekat1.Filip_Draganic_RN542017.R
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }


    private fun init(){
        tabViewPager.adapter = UpperPagerAdapter(supportFragmentManager)
        tabLayout.setupWithViewPager(tabViewPager)


    }

    override fun onBackPressed(){
        super.onBackPressed()
        Timber.e("Nazad")

    }

    private fun initListeners(){

    }


}
