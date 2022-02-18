package com.example.emilikon_oblig2

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.emilikon_oblig2.databinding.ElementBinding

private lateinit var binding: ElementBinding

class PartyAdapter(val liste: MutableList<AlpacaParty>) :
    RecyclerView.Adapter<PartyAdapter.PartyViewHolder>() {


    //provides all the functionality for list items. a wrapper around a view (cardview)
    class PartyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = ElementBinding.bind(view)

        //se FlowersAdapter: setonClickListener()
        //se insertFlower i ViewModel og addFLower() i data source

        fun bindParty(party: AlpacaParty?) {
      //      binding.votes.text = (party?.votes?: "Fant ingen stemmer") as CharSequence?
            binding.partiFarge.setBackgroundColor(Color.parseColor(party?.color?: "#FF0000"))
            binding.name.text = party?.name?: "Fant ikke"
            val leader = party?.leader?: "Fant ikke"
            binding.leader.text = "Leader: $leader"
            //?: "Fant ikke"

            val imageView = binding.img
            Glide.with(imageView.context).load(party?.img?: "Mot found").into(imageView)
        }

     }
        //fyller IKKE med innhold, ikke bundet med spesifikk data
        override fun onCreateViewHolder (viewGroup: ViewGroup, viewType: Int) : PartyViewHolder {

            val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.element, viewGroup, false)
            return PartyViewHolder(view)
        }

    //assosierer viewhHolder med data
    //henter med posision i liste
        override fun onBindViewHolder (viewHolder: PartyViewHolder, position: Int) {

            viewHolder.bindParty(liste[position])

        }

        override fun getItemCount() = liste.size

    }

