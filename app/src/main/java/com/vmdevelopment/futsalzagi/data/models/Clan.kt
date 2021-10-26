package com.vmdevelopment.futsalzagi.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "clanovi_table")
@Parcelize
data class Clan(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var ime: String,
    var prezime: String,
    var oib: String,
    var datumRodjenja: String,
    var datumUclanjenja: String,
    var aktivan: Boolean
): Parcelable