package ru.examples.bilimdonuz.adapters

import android.view.LayoutInflater

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

import ru.examples.bilimdonuz.databinding.BooksItemBinding
import ru.examples.bilimdonuz.model.BooksModel
import ru.examples.bilimdonuz.onclick.IBooksOnClcik


class BooksAdapters(val iBooksOnClcik: IBooksOnClcik):
    ListAdapter<BooksModel,BooksAdapters.BooksVH>(CallbackBooks()) {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksVH {
              //    val view=parent.inflate(LayoutInflater.from(parent.context), R.layout.books_item)
        val view=BooksItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BooksVH(view,iBooksOnClcik)
    }

    override fun onBindViewHolder(holder: BooksVH, position: Int) {
        holder.onBind(getItem(position))
    }

    class BooksVH(val view:BooksItemBinding,iBooksOnClcik: IBooksOnClcik)
        :RecyclerView.ViewHolder(view.root){

        private var booksModel:BooksModel?=null
        init {
            itemView.setOnClickListener {
                booksModel?.let { it1 -> iBooksOnClcik.onClickListener(it1) }
            }
        }
        fun onBind(booksModel: BooksModel){
            this.booksModel=booksModel
            Picasso.get()
                .load(booksModel.image)
                .resize(50, 50)
                .centerCrop()
                .into(view.booksImage)
                  view.booksText.text=booksModel.name
        }
    }
}

class CallbackBooks():DiffUtil.ItemCallback<BooksModel>(){
    override fun areItemsTheSame(oldItem: BooksModel, newItem: BooksModel): Boolean =oldItem==newItem

    override fun areContentsTheSame(oldItem: BooksModel, newItem: BooksModel): Boolean=oldItem.boksid==newItem.boksid

}