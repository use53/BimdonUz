package ru.examples.bilimdonuz.utils

import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class TextStringPreferense(
        private val preferense:SharedPreferences,
        private val key:String,
        private val value:String=""
) :ReadWriteProperty<Any,String>{

    override fun setValue(thisRef: Any, property: KProperty<*>, value: String) {
        preferense.edit().putString(key,value).apply()
    }

    override fun getValue(thisRef: Any, property: KProperty<*>): String =
        preferense.getString(key,"").toString()


}