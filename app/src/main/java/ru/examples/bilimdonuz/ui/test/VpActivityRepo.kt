package ru.examples.bilimdonuz.ui.test

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.preference.PreferenceManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import ru.examples.bilimdonuz.model.AnswerModel
import ru.examples.bilimdonuz.model.BooksModel

class TestVpActivityRepo(val ctx:Context){

    companion object{

        private var instanse: TestVpActivityRepo?=null
        fun instanse(ctx: Context): TestVpActivityRepo {
            if(instanse==null){
                instanse= TestVpActivityRepo(ctx)
            }else{
                instanse
            }
            return instanse!!
        }
    }
    private val firebaseDb by lazy { FirebaseDatabase.getInstance().reference }
    private val _ldAnswer=MutableLiveData<MutableList<AnswerModel>>()
    private val job=Job()
    private val scope= CoroutineScope(job+Dispatchers.IO)
    private val _networkStatus=MutableLiveData<NetworkStatus>()
    fun ComeAnswers(booksModel: String){
        _networkStatus.postValue(NetworkStatus.Loading)
    //  scope.launch {
            val list= arrayListOf<AnswerModel>()
            firebaseDb.child(booksModel).addListenerForSingleValueEvent(object :ValueEventListener{
                override fun onCancelled(error: DatabaseError) {
                    _networkStatus.postValue(NetworkStatus.Error)
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    snapshot.children.forEach {
                        val snp=it.getValue(AnswerModel::class.java)
                       list.add(snp!!)
                    }

                   _ldAnswer.postValue(list)
                    _networkStatus.postValue(NetworkStatus.Success)

                }
            })
      // }
    }

    val ldAnsver:LiveData<MutableList<AnswerModel>>
    get() = _ldAnswer
    
    val networkStatus:LiveData<NetworkStatus>
        get() = _networkStatus

}

sealed class NetworkStatus{
    object Error:NetworkStatus()
    object Success:NetworkStatus()
    object Loading:NetworkStatus()
}