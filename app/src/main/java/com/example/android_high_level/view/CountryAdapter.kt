package com.example.android_high_level.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android_high_level.databinding.ItemCountryBinding
import com.example.android_high_level.model.Country

class CountryAdapter(private var countryList: MutableList<Country>) : RecyclerView.Adapter<CountryAdapter.ViewHolder>() {

    inner class ViewHolder(val viewBinding: ItemCountryBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(country: Country) {
            viewBinding.name.text = country.countryName
            viewBinding.capital.text = country.capital
            Glide.with(viewBinding.imageView.context)
                .load(country.flag)
                .into(viewBinding.imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int =
        countryList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(countryList[position])
    }

    fun modifyList(countryList : MutableList<Country>) {
        this@CountryAdapter.countryList = countryList
        notifyDataSetChanged()
    }
}