package com.example.demoapplication

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ListFragment : Fragment() {

    lateinit var myAdapter: MyAdapter
    lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        linearLayoutManager = LinearLayoutManager(context)

        getMyData()
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)


    }

    private fun getMyData() {

        val apiService = RetrofitInstance.api
        val call =apiService.getData()
        call.enqueue(object : Callback<List<MyDataItem>> {
            override fun onResponse(call: Call<List<MyDataItem>>,
                response: Response<List<MyDataItem>>
            ) {
                if(response.isSuccessful) {
                    val responseBody = response.body()

                    val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerview_users)
                    recyclerView?.setHasFixedSize(true)
                    recyclerView?.layoutManager = linearLayoutManager
                    myAdapter = MyAdapter(context!!, responseBody!!)
                    myAdapter.notifyDataSetChanged()
                    recyclerView?.adapter = myAdapter

                    myAdapter.onItemClick = {
                        val intent = Intent(context, DetailActivity::class.java)
                        intent.putExtra("Data", it)
                        startActivity(intent)
                    }
                }else{
                    Toast.makeText(context,"Error: ${response.code()}",Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<MyDataItem>>, t: Throwable) {

            }

        })


    }


}