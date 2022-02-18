package com.example.emilikon_oblig2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {

    private val dataSource = DataSource()

    private val parties: MutableLiveData<List<AlpacaParty>> by lazy {
        MutableLiveData<List<AlpacaParty>>().also {
            loadParties()
        }
    }

    fun getParties(): LiveData<List<AlpacaParty>> {
        return parties
    }

    fun loadParties() {
        viewModelScope.launch(Dispatchers.IO) {
            dataSource.fetchData().also {
                //returnerer verdi
                parties.postValue(it as List<AlpacaParty>?)
            }
        }
    }


    private val votes: MutableLiveData<List<AlpacaVotes>> by lazy {
        MutableLiveData<List<AlpacaVotes>>().also {
            loadVotes()
        }
    }

    fun getVotes(): LiveData<List<AlpacaVotes>> {
        return votes
    }

    fun loadVotes() {
        viewModelScope.launch(Dispatchers.IO) {
            dataSource.fetchVotes().also {
                //returnerer verdi
                votes.postValue(it)
            }
        }
    }




}
