package ru.examples.bilimdonuz.ui.news

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.*
import ru.examples.bilimdonuz.model.SaveModel
import ru.examples.bilimdonuz.ui.question.QuestionsRepo

class NewsRepo (){

    companion object{

        private var instanse: NewsRepo?=null
        fun instanse(ctx: Context): NewsRepo {
            if(instanse==null){
                instanse= NewsRepo()
            }else{
                instanse
            }
            return instanse!!
        }
    }
    private val firebaseDb by lazy { FirebaseDatabase.getInstance().reference }
    private val job=Job()
    private val scope= CoroutineScope(job+Dispatchers.IO)
    private var ldRead =MutableLiveData<MutableList<SaveModel>>()

    fun firebaseRead(){
        scope.launch {
            val list= mutableListOf<SaveModel>()
            firebaseDb.child("people")
                .addListenerForSingleValueEvent(object :ValueEventListener{
                    override fun onCancelled(error: DatabaseError) {
                        Log.d("TAG", "onCancelled: ${error.code}")
                    }

                    override fun onDataChange(snapshot: DataSnapshot) {
                        snapshot.children.forEach {
                            val snp=it.getValue(SaveModel::class.java)
                            list.add(snp!!)
                        }
                        ldRead.postValue(list)
                    }
                })
        }
    }

    fun ldRead(): MutableLiveData<MutableList<SaveModel>> {
        return ldRead
    }

    fun onCansel(){
        scope.cancel()
    }


}