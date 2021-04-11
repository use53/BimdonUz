package ru.examples.bilimdonuz.ui.test

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.examples.bilimdonuz.model.AnswerModel

class VpActivityViewModel(app:Application) :AndroidViewModel(app){

    private var _ldComeAnswer= MutableLiveData<MutableList<AnswerModel>>()
    private val _networkStatus= MutableLiveData<NetworkStatus>()
    private val testRepo=TestVpActivityRepo.instanse(app.applicationContext)

    fun comeAnswerModel(booksModel: String){
       testRepo.ComeAnswers(booksModel)
       _ldComeAnswer= testRepo.ldAnsver as MutableLiveData<MutableList<AnswerModel>>
       _networkStatus.value=testRepo.networkStatus.value
   }


   val ldComeAnswer: LiveData<MutableList<AnswerModel>>
       get() =_ldComeAnswer
   val networkStatus:LiveData<NetworkStatus>
       get() = _networkStatus

}