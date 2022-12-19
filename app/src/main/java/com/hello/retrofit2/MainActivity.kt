package com.hello.retrofit2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hello.retrofit2.adapter.MyCountryAdapter
import com.hello.retrofit2.MVVM.MainViewModel
import com.hello.retrofit2.MVVM.MainViewModelFactory
import com.hello.retrofit2.model.Country
import com.hello.retrofit2.retrofit.Repository
import com.hello.retrofit2.databinding.ActivityMainBinding
import com.hello.retrofit2.room.CountriesDatabase
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    /*lateinit var mService : RetrofitService
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter : MyCountryAdapter
    lateinit var recyclerCountryList: RecyclerView*/
    //private lateinit var countriesViewModel: CountriesViewModel
    lateinit var layoutManager: LinearLayoutManager
    lateinit var recyclerCountryList: RecyclerView
    lateinit var adapter : MyCountryAdapter
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getPost()
        val textRegion = findViewById<EditText>(R.id.regionTxt)
        lifecycleScope.launch {
            val countriesList = CountriesDatabase(this@MainActivity).getCountryDao().getAllData()
            binding.recyclerCountryView.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = MyCountryAdapter(this@MainActivity, countriesList as MutableList<Country>).apply {
                    setData(countriesList)
                }
            }
        }
        binding.loadBtn.setOnClickListener {
            val countriesList = CountriesDatabase(this@MainActivity).getCountryDao().getAllData()
            CountriesDatabase(this@MainActivity).getCountryDao().deleteCountry()
            val myRegion = textRegion.text.toString()
            viewModel.getCustomPosts(myRegion, "669b686ef485af139a61c5033cde039e")
            viewModel.myCustomPosts.observe(this, Observer {
                    response->
                if (response.isSuccessful){
                    response.body()?.forEach{
                        Log.d("Response", it.alpha2Code.toString())
                        Log.d("Response", it.alpha3Code.toString())
                        Log.d("Response", it.capital.toString())
                        Log.d("Response", it.region.toString())
                        Log.d("Response", "-----------------")
                        val country = Country(id = 0, name = it.name, capital = it.capital, region = it.region)
                        CountriesDatabase(this@MainActivity).getCountryDao().addCountry(country)
                        finish()
                        binding.recyclerCountryView.apply {
                            layoutManager = LinearLayoutManager(this@MainActivity)
                            adapter = MyCountryAdapter(this@MainActivity, countriesList as MutableList<Country>).apply {
                                setData(countriesList)
                            }
                        }
                    }
                }else{
                    Log.d("Response", response.errorBody().toString())
                }

            })

        }

    }
}