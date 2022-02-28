package com.example.emilikon_oblig1

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import com.example.emilikon_oblig1.databinding.ActivityConverterBinding


class ConverterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConverterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConverterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //tastatur: numpad
        //håndtere tekst eller tom input: Toast
        //TextView output: 2 desimaler
        //button(1)-> numpad lukkes



        val knapp = binding.knappKonverter
        val knapp2 = binding.knapp2
        val spinner: Spinner = binding.spinner
        val maaleenheterFraStrings = resources.getStringArray(R.array.maaleenheter)
        var maaleenhetFraBruker = ""

        ArrayAdapter.createFromResource(
            this,
            R.array.maaleenheter,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // layout
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // setter adapter
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                showToast()
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                maaleenhetFraBruker = maaleenheterFraStrings[position]
            }
        }


        knapp.setOnClickListener() {
            val tallFraBruker = parseToDouble(binding.tallInput.text)
            val resultat : Double = konverter(tallFraBruker, maaleenhetFraBruker)
            val resultatAvrundet : Double = Math.round(resultat * 100.0) /100.0
            binding.konvertertResultat.setText("$tallFraBruker $maaleenhetFraBruker tilsvarer $resultatAvrundet L")
            closeKeyboard()

        }

        knapp2.setOnClickListener() {
            val intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
        }
    }

    fun konverter(tall: Double, fraEnhet: String): Double {
        Log.i("Hei", "Hei")
        return when(fraEnhet){
            "fluid ounce(s)" -> (tall*0.02957)
            "cup(s)" -> (tall*0.23659)
            "gallon(s)" -> (tall*3.78541)
            "hogshead(s)" -> tall*238.481
            else -> {
                showToast()
                return 0.0
            }
        }
    }

    fun parseToDouble(value: Editable): Double {
        if(value.isNotEmpty()){
            val result = value.toString().toDoubleOrNull()
            if(result != null)
                return result
            else
                showToast()
                return 0.0
        }else {
            showToast()
            return 0.0
        }
    }

    fun showToast() {
        val text = "Feil input"
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
    }

 //   fun ConverterActivity.closeKeyboard(view: View) {
    fun closeKeyboard() {

        val im: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        im.hideSoftInputFromWindow(currentFocus?.windowToken, InputMethodManager.SHOW_FORCED)

      //  closeKeyboard(View(this))
    }

}