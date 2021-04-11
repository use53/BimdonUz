package ru.examples.bilimdonuz.ui.question

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.examples.bilimdonuz.model.BooksModel

class QuestionsViewModel (app:Application):AndroidViewModel(app){

    private val questionsRepo=QuestionsRepo.instanse(app.applicationContext)
    private var _ldBooksVM=MutableLiveData<MutableList<BooksModel>>()
    private var isCorrect=true

    fun BooksdbVM(){
        if (isCorrect){
        questionsRepo.booksDb()
            isCorrect=false
        }
        _ldBooksVM= questionsRepo.ldBooks()
    }

    val ldBooksVM:LiveData<MutableList<BooksModel>>
    get() = _ldBooksVM




}