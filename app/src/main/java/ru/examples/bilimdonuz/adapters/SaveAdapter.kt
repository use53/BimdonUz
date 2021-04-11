package ru.examples.bilimdonuz.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.examples.bilimdonuz.databinding.SaveLayoutBinding
import ru.examples.bilimdonuz.model.SaveModel


class SaveAdapter ():ListAdapter<SaveModel,SaveAdapter.SaveVH>(SaveCallback()){




     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SaveVH {
          val view=SaveLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
         return SaveVH(view)
    }

    override fun onBindViewHolder(holder: SaveVH, position: Int) {
       holder.onBind(getItem(position))
    }
    class SaveVH(val view:SaveLayoutBinding):
        RecyclerView.ViewHolder(view.root){
        fun onBind(saveModel: SaveModel){
            view.tvCount.text=saveModel.count
            view.tvName.text=saveModel.surname
        }
    }
}

class SaveCallback():DiffUtil.ItemCallback<SaveModel>(){
    override fun areItemsTheSame(oldItem: SaveModel,
                                 newItem: SaveModel): Boolean =oldItem==newItem

    override fun areContentsTheSame(oldItem: SaveModel, newItem: SaveModel): Boolean=oldItem.count==newItem.count
}




