package ru.examples.bilimdonuz.utils

import android.content.Context
import android.content.SharedPreferences
import android.speech.tts.TextToSpeech

private const val SAVENAME="SAVENAME"
private const val SAVESURNAME="SAVESURNAME"
private const val SAVECORRENT="SAVECORRENT"
private const val SAVEAGE="SAVEAGE"
private const val SAVESCIENSE="SAVESCIENSE"

class PreferenseManager (preferenseManager: SharedPreferences):IPreferense{

    override var isSaveName: String by
    TextStringPreferense(preferenseManager, SAVENAME)

    override var isSaveSurName: String by
            TextStringPreferense(preferenseManager, SAVESURNAME)

    override var isCorrent: Boolean by
    CorrentPreferense( preferenseManager,SAVECORRENT)

    override var isAge: String by
    TextStringPreferense(preferenseManager, SAVEAGE)

    override var isSciense: String by
    TextStringPreferense(preferenseManager,SAVESCIENSE)


    companion object{
        private var instanse:IPreferense?=null
        fun instanse(ctx: Context): IPreferense {
            if (instanse==null){
                val preference= androidx.preference.PreferenceManager.getDefaultSharedPreferences(ctx)
                instanse=PreferenseManager(preference)
            }
            return instanse!!
        }
    }
}