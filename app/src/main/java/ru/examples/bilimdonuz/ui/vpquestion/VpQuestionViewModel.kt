package ru.examples.bilimdonuz.ui.vpquestion

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.mikephil.charting.data.PieData
import ru.examples.bilimdonuz.model.AnswerModel
import ru.examples.bilimdonuz.model.SaveModel
import ru.examples.bilimdonuz.ui.savecount.SaveRepo

class VpQuestionViewModel (app:Application):
    AndroidViewModel(app){
private val vpQuestionRepo=VpQuestionRepo.instanse(app.applicationContext)
    private var _ldQuestionVM =MutableLiveData<MutableList<AnswerModel>>()
    private var _statusVm=MutableLiveData<NetworksStatus>()
    private var _isCorrentVm=MutableLiveData<Boolean>()
    private var _saveCount=MutableLiveData<Int>()
    private val saveRepo=SaveRepo()
    private var _ldChartVm=MutableLiveData<PieData>()

    fun onQuestionVM(string:String){
        vpQuestionRepo.onQuestion(string)
        _ldQuestionVM=vpQuestionRepo.ldQuestion()
        _statusVm=vpQuestionRepo.status()
    }
    fun saveItem(count:Int){
        saveRepo.onChartItem(count)
        _ldChartVm=saveRepo.ldChart()
        _saveCount.value=count
    }
    val ldChart:LiveData<PieData>
    get() = _ldChartVm
    val saveCount:LiveData<Int>
    get() = _saveCount

    fun corrent(icCorrent:Boolean){

        _isCorrentVm.value=icCorrent
    }
    val isCorrent:LiveData<Boolean>
        get() = _isCorrentVm

    val ldQuestionVM:LiveData<MutableList<AnswerModel>>
    get() = _ldQuestionVM

    val statusVm:LiveData<NetworksStatus>
        get() =_statusVm

    override fun onCleared() {
        super.onCleared()
        vpQuestionRepo.onBackItem()
    }

    fun onFirebaseSave(saveModel: SaveModel){
        saveRepo.onSaveItem(saveModel)
    }
}
