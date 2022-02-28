package com.example.emilikon_oblig1

import android.content.Intent
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.emilikon_oblig1.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val knapp1 = binding.knapp1
        val knapp2 = binding.knapp2

        knapp1.setOnClickListener() {

            val inputNavnString = binding.editText.text.toString().toLowerCase(Locale.getDefault())
            binding.editText.setText("")

            if (erPalindrom(inputNavnString) == true) {
                binding.resultat.setText("Navnet er et palindrom")
            }
            else {
                binding.resultat.setText("Navnet er ikke et palindrom")
            }


        }

          knapp2.setOnClickListener() {
              val intent = Intent(this, ConverterActivity::class.java)
              startActivity(intent)
              }
          }


    private fun erPalindrom(str: String): Boolean {
        val strListe: List<Char> = str.toList()
        var reversedListe: List<Char> = strListe.reversed()
        var reversedNavn = ""
        for (bokstav in reversedListe) {
            reversedNavn += bokstav
        }
        return str.equals(reversedNavn)
    }


}

