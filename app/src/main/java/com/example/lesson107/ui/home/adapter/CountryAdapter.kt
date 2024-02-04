package com.example.lesson107.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lesson107.R
import com.example.lesson107.data.model.Country
import com.example.lesson107.databinding.ItemCountryBinding
import com.example.lesson107.utils.CountryUtils

class CountryAdapter(
    private val countries: MutableList<Country> = mutableListOf(),
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<CountryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemCountryBinding = ItemCountryBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return countries.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val country = countries[position]
        holder.bindData(country, listener)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(countries: List<Country>) {
        this.countries.clear()
        this.countries.addAll(countries)
        notifyDataSetChanged()
    }

    class ViewHolder(
        private val binding: ItemCountryBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(country: Country, listener: OnItemClickListener) {
            binding.textItemName.text = country.countryName
            binding.textItemCapital.text = country.capital
            val imageUrl = CountryUtils.BASE_URL + CountryUtils.IMAGE_URL + country.flag
            Glide.with(binding.root.context)
                .load(imageUrl)
                .error(R.drawable.vietnam)
                .into(binding.imageItemFlag)
            binding.root.setOnClickListener {
                listener.onItemClick(country)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(country: Country)
    }
}