package com.example.emilikon_oblig1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.emilikon_oblig1.databinding.ActivityConverterBinding
import com.example.emilikon_oblig1.databinding.ActivityQuizBinding
import android.R

import android.graphics.drawable.GradientDrawable



class QuizActivity : AppCompatActivity() {

    var counter = 1

    private lateinit var binding: ActivityQuizBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var pos = 0
        var poeng = 0

        val spm1 = "Seler kan puste under vann"
        val spm2 = "Mount Everest er verdens hoyeste fjell"
        val spm3 = "Flyvefisker bruker vinger til å fly"
        val objekt1 = Question(spm1, false)
        val objekt2 = Question(spm2, true)
        val objekt3 = Question(spm3, false)
        val listeSpm = mutableListOf<Question>(objekt1, objekt2, objekt3)
        val antSpm = listeSpm.size


        binding.poeng.setText("$counter / $antSpm \n Antall poeng er $poeng")

        binding.spm.setText(listeSpm[0].component1())

        binding.fakta.setOnClickListener(){
            counter++
            if (pos < listeSpm.size-1) {
                if (listeSpm[pos].component2() == true) {
                    poeng++
                }
                binding.poeng.setText("$counter / $antSpm \n Antall poeng er $poeng")
                pos++
                if (listeSpm[pos] != null) {
                    binding.spm.setText(listeSpm[pos].component1())
                }
            }else {
                binding.fleip.setAlpha(0.5f)
                binding.fakta.setAlpha(0.5f)
                val antSpm = listeSpm.size
                binding.poeng.setText("Du fikk $poeng poeng!")

            }

        }

        binding.fleip.setOnClickListener() {
            counter++
            if (pos < listeSpm.size-1) {
                if (listeSpm[pos].component2() == false) {
                    poeng++
                    binding.poeng.setText("$counter / $antSpm \n Antall poeng er $poeng")
                }
                binding.poeng.setText("$counter / $antSpm \n Antall poeng er $poeng")
                pos++
                if (listeSpm[pos] != null){
                    binding.spm.setText(listeSpm[pos].component1())
                }

            }
            else {
                if (listeSpm[pos] != null) {
                    if (listeSpm[pos].component2() == false) {
                        poeng++
                    }
                }
                binding.fleip.setAlpha(0.5f)
                binding.fakta.setAlpha(0.5f)
                binding.poeng.setText("Du fikk $poeng poeng!")
            }
            }

        binding.restartQuiz.setOnClickListener() {
            counter = 1
            pos = 0
            poeng = 0
            binding.fleip.setAlpha(1f)
            binding.fakta.setAlpha(1f)
            binding.poeng.setText("$counter / $antSpm \n" +
                    " Antall poeng er $poeng")
            binding.spm.setText(listeSpm[pos].component1())
        }
    }


}

