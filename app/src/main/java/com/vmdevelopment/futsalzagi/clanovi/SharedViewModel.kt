package com.vmdevelopment.futsalzagi.clanovi

import android.app.Application
import android.text.TextUtils
import androidx.lifecycle.AndroidViewModel
import com.vmdevelopment.futsalzagi.data.models.Mjeseci
import java.text.SimpleDateFormat
import java.util.*

class SharedViewModel(application: Application): AndroidViewModel(application) {

    fun provjeriPodatke(ime: String): Boolean {
        return if (TextUtils.isEmpty(ime)) {
            false
        } else !(ime.isEmpty())
    }

    fun provjeriPodatkeClanarine(ime: String, mjesec: String): Boolean {
        return if (TextUtils.isEmpty(ime) || TextUtils.isEmpty(mjesec)) {
            false
        } else !(ime.isEmpty() || mjesec.isEmpty())
    }

    fun parseMjeseci(mjesec: String): Mjeseci {
        return when(mjesec) {
            "Siječanj" -> Mjeseci.Siječanj
            "Veljača" -> Mjeseci.Veljača
            "Ožujak" -> Mjeseci.Ožujak
            "Travanj" -> Mjeseci.Travanj
            "Svibanj" -> Mjeseci.Svibanj
            "Lipanj" -> Mjeseci.Lipanj
            "Srpanj" -> Mjeseci.Srpanj
            "Kolovoz" -> Mjeseci.Kolovoz
            "Rujan" -> Mjeseci.Rujan
            "Listopad" -> Mjeseci.Listopad
            "Studeni" -> Mjeseci.Studeni
            "Prosinac" -> Mjeseci.Prosinac
            else -> Mjeseci.Kolovoz
        }
    }

    fun parseMjeseciToInt(mjesec: Mjeseci): Int {
        return when(mjesec) {
            Mjeseci.Siječanj -> 0
            Mjeseci.Veljača -> 1
            Mjeseci.Ožujak -> 2
            Mjeseci.Travanj -> 3
            Mjeseci.Svibanj -> 4
            Mjeseci.Lipanj -> 5
            Mjeseci.Srpanj -> 6
            Mjeseci.Kolovoz -> 7
            Mjeseci.Rujan -> 8
            Mjeseci.Listopad -> 9
            Mjeseci.Studeni -> 10
            Mjeseci.Prosinac -> 11
        }
    }

    fun trenutniMjesec(): Int {
        val sdf = SimpleDateFormat("M")
        val currentDate = sdf.format(Date())
        return currentDate.toInt()
    }
}