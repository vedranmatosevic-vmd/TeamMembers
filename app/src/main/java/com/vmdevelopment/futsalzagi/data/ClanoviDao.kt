package com.vmdevelopment.futsalzagi.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.vmdevelopment.futsalzagi.data.models.Clan
import com.vmdevelopment.futsalzagi.data.models.Clanarina

@Dao
interface ClanoviDao {

    @Query("SELECT * FROM clanovi_table ORDER BY ime ASC")
    fun dohvatiSveClanove(): LiveData<List<Clan>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun unesiClana(clan: Clan)

    @Update
    suspend fun urediClana(clan: Clan)

    @Delete
    suspend fun obrisiClana(clan: Clan)

    // Dao za drugu tablic clanarine_table
    @Query("SELECT * FROM clanarine_table ORDER BY mjesec ASC")
    fun dohvatiSveClanarine(): LiveData<List<Clanarina>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun unesiClanarinu(clanarina: Clanarina)

    @Update
    suspend fun urediClanarinu(clanarina: Clanarina)

    @Delete
    suspend fun obrisiClanarinu(clanarina: Clanarina)
}