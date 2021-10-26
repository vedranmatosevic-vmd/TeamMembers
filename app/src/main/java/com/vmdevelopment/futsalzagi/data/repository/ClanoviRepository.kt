package com.vmdevelopment.futsalzagi.data.repository

import androidx.lifecycle.LiveData
import com.vmdevelopment.futsalzagi.data.ClanoviDao
import com.vmdevelopment.futsalzagi.data.models.Clan
import com.vmdevelopment.futsalzagi.data.models.Clanarina

class ClanoviRepository(private val clanoviDao: ClanoviDao) {

    val dohvatiSveClanove: LiveData<List<Clan>> = clanoviDao.dohvatiSveClanove()

    suspend fun unesiClana(clan: Clan) {
        clanoviDao.unesiClana(clan)
    }

    suspend fun urediClana(clan: Clan) {
        clanoviDao.urediClana(clan)
    }

    suspend fun obriciClana(clan: Clan) {
        clanoviDao.obrisiClana(clan)
    }

    // Repositori za clanarine
    val dohvatiSveClanarine: LiveData<List<Clanarina>> = clanoviDao.dohvatiSveClanarine()

    suspend fun unesiClanarinu(clanarina: Clanarina) {
        clanoviDao.unesiClanarinu(clanarina)
    }

    suspend fun urediClanarinu(clanarina: Clanarina) {
        clanoviDao.urediClanarinu(clanarina)
    }

    suspend fun obrisiClanarinu(clanarina: Clanarina) {
        clanoviDao.obrisiClanarinu(clanarina)
    }
}