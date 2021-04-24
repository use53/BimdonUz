package ru.examples.bilimdonuz.ui.splash

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.currentCoroutineContext
import ru.examples.bilimdonuz.utils.PreferenseManager

class ViewModelSplash (app:Application):AndroidViewModel(app){
    val splashRepo=SplashRepo()
   private var _currentControlVm=MutableLiveData<InternetControl>()
    private val preferense by lazy { PreferenseManager.instanse(app.applicationContext) }
    private val _isCorrentLd=MutableLiveData<Boolean>()

    fun ControlInternetVM(){

       splashRepo.ControlInternet()
        _currentControlVm=splashRepo.currentControl()
    }

    val currentControlVm:LiveData<InternetControl>
    get() = _currentControlVm


    override fun onCleared() {
        super.onCleared()
        splashRepo.onCansel()
    }

   fun isConrrent(){
      _isCorrentLd.value=preferense.isCorrent

  }

    fun onSavePreferense(name: String, surname: String) {
        if (name.length>5 && surname.length>5){
            preferense.isSaveName=name
            preferense.isSaveSurName=surname
            preferense.isCorrent=true
        }
    }

    val isCorrect:LiveData<Boolean>
    get() = _isCorrentLd

}