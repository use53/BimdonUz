package ru.examples.bilimdonuz.ui.account

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.examples.bilimdonuz.model.Account
import ru.examples.bilimdonuz.utils.PreferenseManager

class AccountViewModel(app:Application):AndroidViewModel(app){

    private val preferense by lazy {
        PreferenseManager.instanse(app.applicationContext) }
    private var _accountReadLd=MutableLiveData<Account>()

    fun accountRead(){
        val account=Account(preferense.isSaveName,preferense.isSaveSurName,preferense.isAge)
        _accountReadLd.value=account
    }

   val accountReadLd:LiveData<Account>
    get() = _accountReadLd

    fun accountWrite(account: Account){
        if (account.name.length>5 && account.surname.length>5){
        preferense.isSaveSurName=account.surname
        preferense.isSaveName=account.name
        preferense.isAge=account.age
        preferense.isCorrent=true
        }
    }
}