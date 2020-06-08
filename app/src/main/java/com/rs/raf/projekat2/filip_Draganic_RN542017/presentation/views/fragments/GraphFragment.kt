package com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.views.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.contract.NotesContract
import com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.viewmodel.NoteViewModel
import com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.viewmodel.UserViewModel
import com.rsrafprojekat1.Filip_Draganic_RN542017.R
import kotlinx.android.synthetic.main.fragment_graf.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class GraphFragment : Fragment(R.layout.fragment_graf){

    private val noteViewModel : NotesContract.ViewModel by sharedViewModel<NoteViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        initUI()
    }

    private fun initUI(){
        noteViewModel.drawSet.observe(viewLifecycleOwner, Observer{
            squareView.setLista(it as MutableList<Int>)
            squareView.invalidate()
        })
    }





    override fun onPause() {
        super.onPause()

    }

    override fun onResume() {
        super.onResume()




    }
}