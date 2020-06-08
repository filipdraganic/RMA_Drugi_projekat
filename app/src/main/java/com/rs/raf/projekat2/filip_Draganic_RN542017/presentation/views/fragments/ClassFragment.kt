package com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.views.fragments

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.rs.raf.projekat2.filip_Draganic_RN542017.data.models.ClassFilter
import com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.contract.ClassContract
import com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.viewmodel.ClassViewModel
import com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.views.recycler.adapter.ClassAdapter
import com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.views.recycler.diff.ClassDiff
import com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.views.states.ClassState
import com.rsrafprojekat1.Filip_Draganic_RN542017.R
import kotlinx.android.synthetic.main.fragment_classes.*
import kotlinx.android.synthetic.main.fragment_classes.searchET
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import timber.log.Timber

class ClassFragment : Fragment(R.layout.fragment_classes){


    private val classViewModel : ClassContract.ViewModel by sharedViewModel<ClassViewModel>() // instanciranje view modela

    private lateinit var classAdapter: ClassAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.e("Class Fragment")
        init()
    }

    private fun init(){
        initObservers()
        initListeners()
        initRecycler()
    }

    private fun initListeners(){

        val days = resources.getStringArray(R.array.spinner_days)

        // access the spinner
        val daySpinner = danSpinner
        if (daySpinner != null) {
            val adapter = context?.let {
                ArrayAdapter(
                    it,
                    android.R.layout.simple_spinner_item, days)
            }
            adapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            daySpinner.adapter = adapter

            }

        val groups = resources.getStringArray(R.array.spinner_groups)

        // access the spinner
        val groupSpinner = grupaSpinner
        if (groupSpinner != null) {
            val adapter = context?.let {
                ArrayAdapter(
                    it,
                    android.R.layout.simple_spinner_item, groups)
            }
            adapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            groupSpinner.adapter = adapter

        }


        traziBtn.setOnClickListener{
            val day = daySpinner.selectedItem.toString()
            val group = groupSpinner.selectedItem.toString()
            val query = searchET.text.toString()

            if (day == "" && group == "" && query == ""){
                Timber.e("AAAAAAAAAAA")
            }



            Timber.e("day =" + day + "group =" + group + "query =" + query + "q")

            val classFilter = ClassFilter(day, group, query)
            classViewModel.getClassByFilter(classFilter)


        }

        }

    private fun initRecycler(){
        rvClasses.layoutManager = LinearLayoutManager(context)
        classAdapter = ClassAdapter(ClassDiff())
        rvClasses.adapter = classAdapter
    }

    private fun initObservers(){
        Timber.e("Class Fragment - Init Observers")

        classViewModel.classesState.observe(viewLifecycleOwner, Observer{
            renderState(it)
//            Timber.e(it.toString())
        })

        classViewModel.getAll()
        classViewModel.fetchAll()
    }


    private fun renderState(state: ClassState){
        when(state){
            is ClassState.Success ->{
                showLoadingState(false)
                classAdapter.submitList(state.classes)

            }
            is ClassState.Error ->{
                showLoadingState(false)
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            is ClassState.DataFetched ->{
                showLoadingState(false)
                Toast.makeText(context, "Friski raspored na raspolaganju", Toast.LENGTH_SHORT).show()

            }
            is ClassState.Loading ->{
                showLoadingState(true)

            }
        }
    }
    private fun showLoadingState(loading: Boolean) {
        searchET.isVisible = !loading
        rvClasses.isVisible = !loading
    }


    override fun onPause() {
        super.onPause()




    }

    override fun onResume() {
        super.onResume()
        traziBtn.callOnClick()


    }
}