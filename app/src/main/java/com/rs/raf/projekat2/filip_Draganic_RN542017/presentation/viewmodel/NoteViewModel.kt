package com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rs.raf.projekat2.filip_Draganic_RN542017.data.models.Notes
import com.rs.raf.projekat2.filip_Draganic_RN542017.data.models.NotesEntity
import com.rs.raf.projekat2.filip_Draganic_RN542017.data.models.NotesFilter
import com.rs.raf.projekat2.filip_Draganic_RN542017.data.repositories.NotesRepository
import com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.contract.NotesContract
import com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.views.states.AddNotesState
import com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.views.states.NotesState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import timber.log.Timber
import java.util.concurrent.TimeUnit

class NoteViewModel(private val notesRepository: NotesRepository): ViewModel(), NotesContract.ViewModel{

    private val subscriptions = CompositeDisposable()

    override val notesState : MutableLiveData<NotesState> = MutableLiveData()
    override val addDone : MutableLiveData<AddNotesState> = MutableLiveData()

    private val publishSubject: PublishSubject<NotesFilter> = PublishSubject.create()


    init{
        Timber.e("Init u NoteViewModel")

        val subscription = publishSubject
            .debounce(200, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .switchMap {
                notesRepository
                    .getNotesByFilter(it)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError {
                        Timber.e("Error in publish subject")
                        Timber.e(it)
                    }
            }
            .subscribe(
                {
                    notesState.value = NotesState.Success(it)
                },
                {
                    notesState.value = NotesState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)

    }


    override fun fetchAll() {
        TODO("Not yet implemented")
    }

    override fun getAll() {
        val subscription = notesRepository
            .getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Timber.e(it.toString())
                    notesState.value = NotesState.Success(it)
                },
                {
                    Timber.e(it.toString())
                    notesState.value = NotesState.Error("Error happened when fetching from db NoteViewModel")
                }
            )
        subscriptions.add(subscription)    }

    override fun getNotesByFilter(notesFilter: NotesFilter) {
        publishSubject.onNext(notesFilter)
    }

    override fun insert(note: Notes) {
        val subscription = notesRepository
            .insert(note)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    addDone.value = AddNotesState.Success
                },
                {
                    addDone.value = AddNotesState.Error("Error happened when adding note NoteViewModel")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)

    }

    override fun update(title: String, content: String) {
        TODO("Not yet implemented")
    }

    override fun archive() {
        TODO("Not yet implemented")
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }


}