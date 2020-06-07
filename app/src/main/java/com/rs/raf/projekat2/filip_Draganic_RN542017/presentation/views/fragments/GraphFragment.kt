package com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.views.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.viewmodel.SharedViewModel
import com.rsrafprojekat1.Filip_Draganic_RN542017.R

class GraphFragment : Fragment(R.layout.fragment_graf){


    private val sharedViewModel : SharedViewModel by activityViewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        initListeners()
    }

    private fun initListeners(){

    }





    override fun onPause() {
        super.onPause()



    }

    override fun onResume() {
        super.onResume()




    }
}