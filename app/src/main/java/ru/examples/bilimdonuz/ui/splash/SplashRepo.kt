package ru.examples.bilimdonuz.ui.splash

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.*
import ru.examples.bilimdonuz.utils.PreferenseManager

class SplashRepo (){

    private val _ldCurrent =MutableLiveData<InternetControl>()
    private val firebaseDb=FirebaseDatabase.getInstance().getReference(".info/connected")
    private val job= Job()
    private val scope= CoroutineScope(Dispatchers.IO+job)



    fun ControlInternet() {
        scope.launch {
                firebaseDb.addValueEventListener(object :ValueEventListener{
                    override fun onCancelled(error: DatabaseError) {
                        Log.d("TAG", "onCancelled: ${error.code}")
                    }

                    override fun onDataChange(snapshot: DataSnapshot) {
                        val connect=snapshot.getValue(Boolean::class.java)?:false
                        if (connect){
                            _ldCurrent.postValue(InternetControl.online)
                        }else{
                            _ldCurrent.postValue(InternetControl.offline)
                        }
                    }
                })

        }

    }
    fun currentControl(): MutableLiveData<InternetControl> {
        return _ldCurrent
    }
    fun onCansel(){
        scope.cancel()
    }

}
sealed class InternetControl(){
    object offline:InternetControl()
    object online:InternetControl()
}