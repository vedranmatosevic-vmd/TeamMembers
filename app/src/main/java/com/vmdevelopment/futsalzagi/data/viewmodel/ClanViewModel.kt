package com.vmdevelopment.futsalzagi.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.vmdevelopment.futsalzagi.data.BazaPodataka
import com.vmdevelopment.futsalzagi.data.models.Clan
import com.vmdevelopment.futsalzagi.data.models.Clanarina
import com.vmdevelopment.futsalzagi.data.repository.ClanoviRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ClanViewModel(application: Application): AndroidViewModel(application) {

    private val clanoviDao = BazaPodataka.stvoriBazuPodataka(application).clanoviDao()
    private val repository: ClanoviRepository

    val dohvatiSveClanove: LiveData<List<Clan>>
    val dohvatiSveClanarine: LiveData<List<Clanarina>>

    init {
        repository = ClanoviRepository(clanoviDao)
        dohvatiSveClanove = repository.dohvatiSveClanove
        dohvatiSveClanarine = repository.dohvatiSveClanarine
    }

    fun unesiClana(clan: Clan) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.unesiClana(clan)
        }
    }

    fun urediClana(clan: Clan) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.urediClana(clan)
        }
    }

    fun obrisiClana(clan: Clan) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.obriciClana(clan)
        }
    }

    // Funkcije za clanarine
    fun unesiClanarinu(clanarina: Clanarina) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.unesiClanarinu(clanarina)
        }
    }

    fun urediClanarinu(clanarina: Clanarina) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.urediClanarinu(clanarina)
        }
    }

    fun obrisiClanarinu(clanarina: Clanarina) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.obrisiClanarinu(clanarina)
        }
    }

}