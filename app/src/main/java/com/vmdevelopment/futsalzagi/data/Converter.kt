package com.vmdevelopment.futsalzagi.data

import androidx.room.TypeConverter
import com.vmdevelopment.futsalzagi.data.models.Clan
import com.vmdevelopment.futsalzagi.data.models.Mjeseci

class Converter {

    @TypeConverter
    fun fromMjeseci(mjesec: Mjeseci): String {
        return mjesec.name
    }

    @TypeConverter
    fun toMjeseci(mjesec: String): Mjeseci {
        return Mjeseci.valueOf(mjesec)
    }

}