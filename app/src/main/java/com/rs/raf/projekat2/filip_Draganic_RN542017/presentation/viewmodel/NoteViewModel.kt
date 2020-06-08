package com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rs.raf.projekat2.filip_Draganic_RN542017.data.models.Notes
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
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.time.days

class NoteViewModel(private val notesRepository: NotesRepository): ViewModel(), NotesContract.ViewModel{

    private val subscriptions = CompositeDisposable()

    override val notesState : MutableLiveData<NotesState> = MutableLiveData()
    override val addDone : MutableLiveData<AddNotesState> = MutableLiveData()

    override val drawSet: MutableLiveData<List<Int>> = MutableLiveData()


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
        subscriptions.add(subscription)

    }

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

    override fun update(id: Long, title: String, content: String) {
        val subscription = notesRepository
            .update(id, title, content)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    notesState.value = NotesState.Update
                },
                {
                    notesState.value = NotesState.Error("Error when updating")
                }
            )
        subscriptions.add(subscription)
    }

    override fun archive(id: Long, archive: Int) {
        val subscription = notesRepository
            .archive(id,  archive)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    notesState.value = NotesState.Update
                },
                {
                    notesState.value = NotesState.Error("Error from Archive")
                    Timber.e(it)
                }
            )

        subscriptions.add(subscription)

    }

    override fun delete(id: Long) {
        val subscription = notesRepository
            .delete(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    notesState.value = NotesState.Delete
                },
                {
                    notesState.value = NotesState.Error("Nije uspelo brisanje")
                    Timber.e(it)

                }

            )
        subscriptions.add(subscription)

    }

    override fun drawSetData() {

        val subscription = notesRepository
            .getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Timber.e(it.toString())
                    val zaCrtanje : MutableList<Int> = mutableListOf()
                    Timber.e("DRAW SET DATA OVDE ->>>>>>>>>>>>>>>>>>>")
                    val sdf= SimpleDateFormat("d", Locale.getDefault())

                    val sada = Date()

                    val hashMap = mutableMapOf<String, Int>()

                    for(i in 0..5){
                        hashMap[sdf.format(getDaysAgo(i))] = 0
                        zaCrtanje.add(i, 0)
                    }


                    for(note in it){
                        Timber.e(sdf.format(note.dateCreated))
                        var broj = hashMap[sdf.format(note.dateCreated)] ?: 0

                        broj = broj + 1

                        hashMap[sdf.format(note.dateCreated)] = broj

                        if (sada.time - note.dateCreated.time > 432000000){
                            break
                        }
                    }

                    for(i in 0..5){
                        zaCrtanje[i] = hashMap[sdf.format(getDaysAgo(i))]!!
                    }
                    Timber.e(hashMap.toString())

                    Timber.e(zaCrtanje.toString())
                    drawSet.value = zaCrtanje

                },
                {
                    Timber.e(it.toString())
                }
            )
        subscriptions.add(subscription)
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }

    fun getDaysAgo(daysAgo: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -daysAgo)

        return calendar.time
    }
}