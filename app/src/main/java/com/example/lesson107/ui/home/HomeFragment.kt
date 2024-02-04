package com.example.lesson107.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.lesson107.R
import com.example.lesson107.data.model.Country
import com.example.lesson107.data.source.CountryDataRepository
import com.example.lesson107.data.source.local.CountryDatabase
import com.example.lesson107.data.source.local.LocalDataSource
import com.example.lesson107.data.source.remote.RemoteDataSource
import com.example.lesson107.databinding.FragmentHomeBinding
import com.example.lesson107.ui.detail.DetailFragment
import com.example.lesson107.ui.detail.viewmodel.CountryItemViewModel
import com.example.lesson107.ui.home.adapter.CountryAdapter
import com.example.lesson107.ui.home.viewmodel.CountryViewModel
import com.example.lesson107.ui.home.viewmodel.CountryViewModelFactory
import com.example.lesson107.utils.CountryUtils


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: CountryAdapter
    private lateinit var countryViewModel: CountryViewModel
    private lateinit var shareViewModel: CountryItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbarHome.title = getString(R.string.title_home)
        setupViews()
        setupViewModel()
    }

    override fun onPause() {
        super.onPause()
        countryViewModel.cacheData()
    }

    private fun setupViews() {
        val callback = object : CountryAdapter.OnItemClickListener {
            override fun onItemClick(country: Country) {
                shareViewModel.updateSelectedCountry(country)
                switchToDetailFragment()
            }

        }
        adapter = CountryAdapter(listener = callback)
        binding.recyclerCountry.adapter = adapter
        val divider = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        AppCompatResources.getDrawable(
            requireContext(),
            R.drawable.list_divider
        )
            ?.let { divider.setDrawable(it) }
        binding.recyclerCountry.addItemDecoration(divider)
    }

    private fun switchToDetailFragment() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, DetailFragment::class.java, null)
            .addToBackStack(null)
            .setReorderingAllowed(true)
            .commit()
    }

    private fun setupViewModel() {
        val localDataSource = LocalDataSource(CountryDatabase.instance(requireContext()))
        val remoteDataSource = RemoteDataSource()
        val selectedDataSource = CountryUtils.checkInternetState(requireContext())
        val repository = CountryDataRepository.Builder()
            .setLocalDataSource(localDataSource)
            .setRemoteDataSource(remoteDataSource)
            .setSelectedDataSource(selectedDataSource)
            .build()
        CountryUtils.selectedDataSource = selectedDataSource
        countryViewModel = ViewModelProvider(
            this,
            CountryViewModelFactory(repository)
        )[CountryViewModel::class.java]
        countryViewModel.countries.observe(viewLifecycleOwner){
            adapter.updateData(it)
        }
        shareViewModel = ViewModelProvider(requireActivity())[CountryItemViewModel::class.java]
    }
}