package com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rs.raf.projekat2.filip_Draganic_RN542017.data.models.ClassFilter
import com.rs.raf.projekat2.filip_Draganic_RN542017.data.models.Resource
import com.rs.raf.projekat2.filip_Draganic_RN542017.data.repositories.ClassRepository
import com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.contract.ClassContract
import com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.views.states.ClassState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import timber.log.Timber
import java.util.concurrent.TimeUnit

class ClassViewModel(private val classRepository: ClassRepository) : ViewModel(), ClassContract.ViewModel {

    private val subscriptions = CompositeDisposable()

    override val classesState : MutableLiveData<ClassState> = MutableLiveData()


    private val publishSubject: PublishSubject<ClassFilter> = PublishSubject.create()

    init {
        Timber.e("Init u ClassViewModelu")

        val subscription = publishSubject
            .debounce(200, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .switchMap {
                classRepository
                    .getClassByFilter(it)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError{
                        Timber.e("Error in publish subject ClassViewModel")
//                        Timber.e(it)
                    }
            }
            .subscribe(
                {
                    classesState.value = ClassState.Success(it)
                },
                {
                    classesState.value = ClassState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }


    override fun fetchAll() {

        val subscription = classRepository
            .fetchAll()
            .startWith(Resource.Loading())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                when(it){
                    is Resource.Loading -> classesState.value = ClassState.Loading
                    is Resource.Success -> classesState.value = ClassState.DataFetched
                    is Resource.Error -> classesState.value = ClassState.Error("Error happened while fetching data 1")
                    }
                },
                {
                    classesState.value = ClassState.Error("Error Happened fetching data 2")
                    Timber.e(it)
                })

        subscriptions.add(subscription)
    }

    override fun getAll() {

        val subscription = classRepository
            .getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    classesState.value = ClassState.Success(it)
                },
                {
                    classesState.value = ClassState.Error("Error happened when fething data from db 3")
                }
            )
        subscriptions.add(subscription)
    }

    override fun getClassByFilter(classFilter: ClassFilter) {
        publishSubject.onNext(classFilter)
    }



    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }

}