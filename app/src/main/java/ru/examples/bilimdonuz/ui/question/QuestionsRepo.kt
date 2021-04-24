package ru.examples.bilimdonuz.ui.question

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.*
import ru.examples.bilimdonuz.model.BooksModel

class QuestionsRepo(ctx:Context) {

    companion object{

        private var instanse:QuestionsRepo?=null
        fun instanse(ctx: Context):QuestionsRepo{
            if(instanse==null){
                instanse= QuestionsRepo(ctx)
            }else{
                instanse
            }
            return instanse!!
            }
        }

    private val firebaseDB=FirebaseDatabase.getInstance().reference
    private val job= Job()
    private val scope=CoroutineScope(job+Dispatchers.IO)
    private val _ldBooks=MutableLiveData<MutableList<BooksModel>>()

    fun  booksDb(){
        scope.launch {
            val list= mutableListOf<BooksModel>()
            firebaseDB.child("books").addListenerForSingleValueEvent(object :ValueEventListener{
                override fun onCancelled(error: DatabaseError) {
                    Log.d("TAG", "onCancelled:${error.code} ")
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    snapshot.children.forEach {
                        val snp=it.getValue(BooksModel::class.java)
                        list.add(snp!!)
                    }
                    _ldBooks.postValue(list)
                }
            })
        }
    }
     fun ldBooks(): MutableLiveData<MutableList<BooksModel>> {
         return _ldBooks
     }

    fun onCansel(){
        scope.cancel()
    }


}


