package com.example.emilikon_oblig2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.emilikon_oblig2.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.InputStream

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val baseUrl =
            "https://www.uio.no/studier/emner/matnat/ifi/IN2000/v22/obligatoriske-oppgaver/district3.xml"
        //   val token = "token"

        fun getData(): String {
            val requestUrl = "$baseUrl"
            return khttp.get(requestUrl).text

        }

        CoroutineScope(Dispatchers.IO).launch {
            val response = getData()
            Log.d(response, response)


            val inputStream: InputStream = response.byteInputStream()
            val listOfVotes = AlpacaParser().parse(inputStream)


            listOfVotes[1].id?.let { Log.i("INNHOLD", it) }


        }

        viewModel.getParties().observe(this) { parties ->
            val listeParties = parties.toMutableList()

            binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {

                    binding.recyclerViewMain.apply {
                        layoutManager = LinearLayoutManager(applicationContext)
                        adapter = PartyAdapter(listeParties)
                    }
                }

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                //    val selected = parent?.getItemAtPosition(position).toString()
                //    if (selected) {
                //        "Valgdistrikt 1" -> getDataValgdistrikt1()
                //        "Valgdistrikt 2" -> getDataValgdistrikt2()
                //     }else {
                 //        null
                 //   }
                 //   }
                    binding.recyclerViewMain.apply {
                        layoutManager = LinearLayoutManager(applicationContext)
                        adapter = PartyAdapter(listeParties)
                    }
                }

            } //viewModel avsluttes her
    }
    }
}

