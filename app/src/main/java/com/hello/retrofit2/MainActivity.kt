package com.hello.retrofit2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    /*lateinit var mService : RetrofitService
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter : MyCountryAdapter
    lateinit var recyclerCountryList: RecyclerView*/
    lateinit var layoutManager: LinearLayoutManager
    lateinit var recyclerCountryList: RecyclerView
    lateinit var adapter : MyCountryAdapter
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val repository = Repository()
        val textName = findViewById<TextView>(R.id.textViewCountry)
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getPost()

        val buttonLoad = findViewById<Button>(R.id.buttonLoad)
        val textRegion = findViewById<EditText>(R.id.textRegion)
        buttonLoad.setOnClickListener{
            val myRegion = textRegion.text.toString()
            viewModel.getCustomPosts(myRegion, "7f7b5d0f0a8769ebdcc4a8ec2a4ea12e")
            viewModel.myCustomPosts.observe(this, Observer {
                    response->
                if (response.isSuccessful){
                    //Log.d("Response", response.body()?.name.toString())
                    recyclerCountryList = findViewById<RecyclerView>(R.id.recyclerCountryList)
                    recyclerCountryList.setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(this)
                    recyclerCountryList.layoutManager = layoutManager
                    adapter = MyCountryAdapter(baseContext, response.body() as MutableList<Country>)
                    adapter.notifyDataSetChanged()
                    recyclerCountryList.adapter = adapter
                    //textName.text = response.body()?.toString()
                    response.body()?.forEach{
                        //Log.d("Response", it.topLevelDomain.toString())
                        Log.d("Response", it.alpha2Code.toString())
                        Log.d("Response", it.alpha3Code.toString())
                        //Log.d("Response", it.callingCodes.toString())
                        Log.d("Response", it.capital.toString())
                        //Log.d("Response", it.altSpellings.toString())
                        Log.d("Response", it.region.toString())
                        Log.d("Response", "-----------------")
                    }
                    /*Log.d("Response", response.body()?.topLevelDomain.toString())
                    Log.d("Response", response.body()?.alpha2Code.toString())
                    Log.d("Response", response.body()?.alpha3Code.toString())
                    Log.d("Response", response.body()?.callingCodes.toString())
                    Log.d("Response", response.body()?.capital.toString())
                    Log.d("Response", response.body()?.altSpellings.toString())
                    Log.d("Response", response.body()?.region.toString())*/
                }else{
                    //Log.d("Response", response.errorBody().toString())
                    textName.text = response.code().toString()
                }

            })
        }
        /*mService = Common.retrofitService
        recyclerCountryList = findViewById<RecyclerView>(R.id.recyclerCountryList)
        recyclerCountryList.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        recyclerCountryList.layoutManager = layoutManager
        getAllCountryList()*/
    }
    /*private fun getAllCountryList() {
        //dialog.show()
        mService.getCountryList().enqueue(object : retrofit2.Callback<MutableList<Country>> {
            override fun onFailure(call: Call<MutableList<Country>>, t: Throwable) {

            }

            override fun onResponse(call: Call<MutableList<Country>>, response: Response<MutableList<Country>>) {
                adapter = MyCountryAdapter(baseContext, response.body() as MutableList<Country>)
                adapter.notifyDataSetChanged()
                recyclerCountryList.adapter = adapter


            }

        })
    }*/
}