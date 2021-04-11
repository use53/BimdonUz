package ru.examples.bilimdonuz.ui.news

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.examples.bilimdonuz.model.SaveModel

class NewsViewModel (app:Application):AndroidViewModel(app){

    private val newsRepo=NewsRepo()
    private var _ldRead=MutableLiveData<MutableList<SaveModel>>()
    fun firebaseRead(){
        newsRepo.firebaseRead()
        _ldRead=newsRepo.ldRead()
    }

    val ldRead:LiveData<MutableList<SaveModel>>
    get() = _ldRead
}