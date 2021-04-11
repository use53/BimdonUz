package ru.examples.bilimdonuz.ui.vpquestion

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.*
import ru.examples.bilimdonuz.model.AnswerModel
import ru.examples.bilimdonuz.ui.test.NetworkStatus
import ru.examples.bilimdonuz.ui.test.TestVpActivityRepo

class VpQuestionRepo(ctx:Context) {

    companion object{

        private var instanse: VpQuestionRepo?=null
        fun instanse(ctx: Context): VpQuestionRepo {
            if(instanse==null){
                instanse= VpQuestionRepo(ctx)
            }else{
                instanse
            }
            return instanse!!
        }
    }
    private val firebaseDb by lazy { FirebaseDatabase.getInstance().reference }
    private var ldQuestion=MutableLiveData<MutableList<AnswerModel>>()
    private val job= Job()
    private val scope= CoroutineScope(job+ Dispatchers.IO)
    private val status=MutableLiveData<NetworksStatus>()
    fun onQuestion(string:String){
        status.postValue(NetworksStatus.Loading)
         scope.launch {
        val list= arrayListOf<AnswerModel>()
        firebaseDb.child(string).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                 status.postValue(NetworksStatus.Error)
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach {
                    val snp=it.getValue(AnswerModel::class.java)
                    list.add(snp!!)
                }
                status.postValue(NetworksStatus.Success)
             ldQuestion.postValue(list)

            }
        })
       }
    }

    fun ldQuestion():MutableLiveData<MutableList<AnswerModel>>{
        return ldQuestion
    }
    fun status():MutableLiveData<NetworksStatus>{
        return status
    }

   fun onBackItem(){
       scope.cancel()
   }
}

sealed class NetworksStatus{
    object Error:NetworksStatus()
    object Success:NetworksStatus()
    object Loading:NetworksStatus()
}