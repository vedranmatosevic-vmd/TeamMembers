package com.vmdevelopment.futsalzagi.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "clanarine_table")
@Parcelize
data class Clanarina(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var clan: String,
    var mjesec: Mjeseci,
    var iznos: Int,
    var placeno: Boolean
): Parcelable
