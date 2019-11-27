package com.utch.apiconsume

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var  layoutManager: RecyclerView.LayoutManager
    private var productsArr = ArrayList<Products>()
    private lateinit var adapter :Adapter

    private fun getRetrofit(): Retrofit {
        //En baseUrl va mi ip con el puerto del server en el endpoint: /products
        return Retrofit.Builder()
            .baseUrl("http://35.196.35.146:8000")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getProducts() {
        doAsync {
            val call = getRetrofit().create(API::class.java).get()

            call.enqueue(object: Callback<Padre>{
                override fun onFailure(call: Call<Padre>, t: Throwable) {
                    Log.v("retrofit", "Errorsillo: ${t.message}")
                }

                override fun onResponse(call: Call<Padre>, response: Response<Padre>) {
                    if(response.code() == 200){
                        val (productos) = response.body()!!

                        adapter = Adapter(this@MainActivity, productos)
                        rvProducts.setHasFixedSize(true)
                        rvProducts.layoutManager = LinearLayoutManager(this@MainActivity)
                        rvProducts.adapter = adapter

                    }
                }

            })

        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getProducts()
    }
}
