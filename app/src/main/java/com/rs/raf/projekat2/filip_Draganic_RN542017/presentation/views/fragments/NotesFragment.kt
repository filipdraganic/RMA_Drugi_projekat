package com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.views.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.rs.raf.projekat2.filip_Draganic_RN542017.data.models.Notes
import com.rs.raf.projekat2.filip_Draganic_RN542017.data.models.NotesFilter
import com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.contract.NotesContract
import com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.viewmodel.NoteViewModel
import com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.views.activity.NotesActivity
import com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.views.recycler.adapter.NotesAdapter
import com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.views.recycler.diff.NotesDiff
import com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.views.states.NotesState
import com.rsrafprojekat1.Filip_Draganic_RN542017.R
import kotlinx.android.synthetic.main.fragment_notes.*
import kotlinx.android.synthetic.main.fragment_notes.searchET
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import timber.log.Timber
import java.util.*

class NotesFragment : Fragment(R.layout.fragment_notes){

    private val noteViewModel : NotesContract.ViewModel by sharedViewModel<NoteViewModel>()

    private lateinit var notesAdapter: NotesAdapter

    companion object{
        const val SIFRA = "SIFRA"
        const val NEWKOD = 1337
        const val EDITKOD = 42069
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.e("Notes Fragment")

        init()
    }

    private fun init(){
        initObservers()
        initListeners()
        initRecycler()
    }

    private fun initObservers(){
        Timber.e("Notes Fragment - Init Observers")


        noteViewModel.notesState.observe(viewLifecycleOwner, Observer{
            noteViewModel.drawSetData()
            renderState(it)
            Timber.e(it.toString())
        })




        var toggle = false
        if(toggleBtn.isChecked)
            toggle = true

        noteViewModel.getNotesByFilter(NotesFilter(searchQuery = searchET.text.toString(), creator = 1, isArchived = toggle))


    }

    private fun renderState(state: NotesState){
        when(state){
            is NotesState.Success ->{
                showLoadingState(false)
                notesAdapter.submitList(state.notes)

            }
            is NotesState.Error ->{
                showLoadingState(false)
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            is NotesState.DataFetched ->{
                showLoadingState(false)
                Toast.makeText(context, "Friske beleske su stigle", Toast.LENGTH_SHORT).show()

            }
            is NotesState.Loading ->{
                showLoadingState(true)

            }
        }
    }
    private fun showLoadingState(loading: Boolean) {
        searchET.isVisible = !loading
        rvNotes.isVisible = !loading
    }


    private fun initListeners(){

        searchET.doAfterTextChanged {
            var toggle = false
            if(toggleBtn.isChecked)
                toggle = true

            noteViewModel.getNotesByFilter(NotesFilter(searchQuery = searchET.text.toString(), creator = 1, isArchived = toggle))
        }

        toggleBtn.setOnClickListener {
            var toggle = false
            if(toggleBtn.isChecked)
                toggle = true

            noteViewModel.getNotesByFilter(NotesFilter(searchQuery = searchET.text.toString(), creator = 1, isArchived = toggle))
        }


        floatingBtn.setOnClickListener{

            val intent = Intent(context, NotesActivity::class.java)
            intent.putExtra("requestCode", NEWKOD.toString())
            startActivityForResult(intent, NEWKOD)

        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        Timber.e("GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG")

        if(requestCode == NEWKOD){
            if(resultCode == Activity.RESULT_OK){
                val title = data?.getStringExtra("title")
                val content = data?.getStringExtra("content")
                Timber.e(title.toString() + " "+ content.toString())

                noteViewModel.insert(Notes(0, 1, Date(), title.toString(), content.toString(), 0 ))

            }
        }

        if(requestCode == EDITKOD){
            if(resultCode == Activity.RESULT_OK){
                val title = data?.getStringExtra("title")
                val content = data?.getStringExtra("content")
                val id = data?.getLongExtra("id", 1)
                Timber.e(title.toString() + " "+ content.toString() + " " + id.toString())

                if (id != null) {
                    noteViewModel.update(id , title.toString(), content.toString())
                }else{
                    Timber.e("Neuspelo editovanje")
                }

            }
        }
    }

    private val archiveButton: (Notes) -> Unit = {
        var archive = 0
        if(it.isArchived == 0) archive = 1

        noteViewModel.archive(it.id, archive)

    }

    private val deleteButton: (Notes) -> Unit = {

        noteViewModel.delete(it.id)

    }


    private val editButton: (Notes) -> Unit = {

        val intent = Intent(context, NotesActivity::class.java)
        intent.putExtra("requestCode", EDITKOD.toString())
        intent.putExtra("title", it.title)
        intent.putExtra("content", it.content)
        intent.putExtra("id", it.id)
        startActivityForResult(intent, EDITKOD)

    }



    private fun initRecycler(){

        rvNotes.layoutManager = LinearLayoutManager(activity)
        notesAdapter = NotesAdapter(NotesDiff(), archiveButton, deleteButton, editButton)
        rvNotes.adapter = notesAdapter

    }

    override fun onPause() {
        super.onPause()



    }

    override fun onResume() {
        super.onResume()


    }
}