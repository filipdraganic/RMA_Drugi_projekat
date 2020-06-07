package com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.views.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.rs.raf.projekat2.filip_Draganic_RN542017.data.models.Pacijent
import com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.viewmodel.SharedViewModel
import com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.views.recycler.adapter.NotesAdapter
import com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.views.recycler.diff.PacijentDiff
import com.rsrafprojekat1.Filip_Draganic_RN542017.R
import kotlinx.android.synthetic.main.fragment_notes.*

class NotesFragment : Fragment(R.layout.fragment_notes){
    private val sharedViewModel : SharedViewModel by activityViewModels()

    private lateinit var notesAdapter: NotesAdapter

    companion object{
        const val PACIJENT = "PACIJENT"
        const val PACIJENT_KOD = 1337
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        initObservers()
        initListeners()
        initRecycler()
    }

    private fun initObservers(){
        sharedViewModel.getHospitalizovaniData().observe(viewLifecycleOwner, Observer {
            notesAdapter.submitList(it)
            notesAdapter.notifyDataSetChanged()
        })

    }

    private fun initListeners(){

        searchET.doAfterTextChanged {
            sharedViewModel.pretraziPacijenta(SharedViewModel.HOSPITALIZOVAN, searchET.text.toString())
            notesAdapter.notifyDataSetChanged()
        }

    }

    private val kartonDugme : (Pacijent) ->Unit = {


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


    }

    private val otpustiDugme : (Pacijent) -> Unit = {
        sharedViewModel.premestiPacijenta(it, SharedViewModel.HOSPITALIZOVAN, SharedViewModel.OTPUSTEN)
        sharedViewModel.pretraziPacijenta(SharedViewModel.HOSPITALIZOVAN, searchET.text.toString())
        notesAdapter.notifyDataSetChanged()
    }

    private fun initRecycler(){

        rvHospitalizovani.layoutManager = LinearLayoutManager(activity)
        notesAdapter = NotesAdapter(PacijentDiff(), kartonDugme, otpustiDugme)
        rvHospitalizovani.adapter = notesAdapter

    }

    override fun onPause() {
        super.onPause()



    }

    override fun onResume() {
        super.onResume()
        sharedViewModel.pretraziPacijenta(SharedViewModel.HOSPITALIZOVAN, searchET.text.toString())


    }
}