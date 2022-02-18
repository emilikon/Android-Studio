package com.example.emilikon_oblig2

data class AlpacaParty (val id: String?, val name: String?, val  leader: String?, val img: String?, val color: String?, var votes: Int?) {}

data class Base (val parties : List<AlpacaParty?>) {}