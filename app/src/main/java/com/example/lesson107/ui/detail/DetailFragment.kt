package com.example.lesson107.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.lesson107.R
import com.example.lesson107.data.model.Country
import com.example.lesson107.databinding.FragmentDetailBinding
import com.example.lesson107.ui.detail.viewmodel.CountryItemViewModel
import com.example.lesson107.utils.CountryUtils


class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolBar()
        setupViewModel()
    }

    private fun setupToolBar(){
        binding.toolbarDetail.setNavigationIcon(R.drawable.ic_up)
        binding.toolbarDetail.setNavigationOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun setupViewModel(){
        val viewModel = ViewModelProvider(requireActivity())[CountryItemViewModel::class.java]
        viewModel.country.observe(viewLifecycleOwner){
            showCountryDetail(it)
        }
    }

    private fun showCountryDetail(country : Country) {
        val countryName = getString(R.string.title_nation_detail, country.countryName)
        val capital = getString(R.string.title_capital_detail, country.capital)
        val population = getString(
            R.string.title_population,
            CountryUtils.formatNumber(country.population)
        )
        val area = getString(
            R.string.title_area,
            CountryUtils.formatNumber(country.area.toFloat())
        )
        val worldShare = getString(R.string.title_world_share, country.worldShare)
        val density = getString(
            R.string.title_density,
            CountryUtils.formatNumber(country.density.toFloat())
        )
        // đổ dữ liệu lên view
        binding.toolbarDetail.title = country.countryName
        binding.textNationName.text = countryName
        binding.textCapitalDetail.text = capital
        binding.textPopulation.text = population
        binding.textArea.text = area
        binding.textWorldShare.text = worldShare
        binding.textDensity.text = density
        val imageUrl = CountryUtils.BASE_URL + CountryUtils.IMAGE_URL + country.flag
        Glide.with(this)
            .load(imageUrl)
            .error(R.drawable.vietnam)
            .into(binding.imageFlag)
    }
}