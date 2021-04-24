package ru.examples.bilimdonuz.utils

import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class CorrentPreferense(
        private val preferense:SharedPreferences,
        private val key:String,
        private val value:Boolean=false
):ReadWriteProperty<Any,Boolean>{
    override fun setValue(thisRef: Any, property: KProperty<*>, value: Boolean) {
        preferense.edit().putBoolean(key,value).apply()
    }

    override fun getValue(thisRef: Any, property: KProperty<*>): Boolean =
        preferense.getBoolean(key,false)

}