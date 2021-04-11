package ru.examples.bilimdonuz.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

fun ViewGroup.inflate(inflater: LayoutInflater, @LayoutRes id:Int): View {
    return inflater.inflate(id,this,false)
}