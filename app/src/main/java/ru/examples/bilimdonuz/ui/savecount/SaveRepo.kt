package ru.examples.bilimdonuz.ui.savecount

import android.content.Context
import android.graphics.Color
import androidx.lifecycle.MutableLiveData
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.google.firebase.database.FirebaseDatabase
import ru.examples.bilimdonuz.model.SaveModel

class SaveRepo (){
    companion object{

        private var instanse: SaveRepo?=null
        fun instanse(ctx: Context): SaveRepo {
            if(instanse==null){
                instanse= SaveRepo()
            }else{
                instanse
            }
            return instanse!!
        }

    }

    private val list= mutableListOf<Int>().apply {
        this.add(Color.RED)
        this.add(Color.GREEN)
    }
    private var ldChart=MutableLiveData<PieData>()
    private val firabseDb by lazy { FirebaseDatabase.getInstance().reference }

    fun onChartItem(count:Int){
        val pieDataSet= PieDataSet(onChart(count),"")
        pieDataSet.colors=list
        val pieData= PieData(pieDataSet)
        ldChart.value=pieData
    }
    fun ldChart():MutableLiveData<PieData>{
        return ldChart
    }

    fun onChart(count: Int):MutableList<PieEntry>{
        val countr=count.toFloat()
        val item=20.minus(count).toFloat()
        val list= mutableListOf<PieEntry>()
        list.add(PieEntry(item,"Xato"))
        list.add(PieEntry(countr,"To'g'ri"))
        return list
    }

    fun onSaveItem(saveModel: SaveModel){
        firabseDb.child("people")
            .child(saveModel.surname)
            .setValue(saveModel)


    }

}