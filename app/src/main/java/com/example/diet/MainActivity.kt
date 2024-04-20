package com.example.diet

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val button = findViewById<ImageView>(R.id.RightArrow)

        button.setOnClickListener {
            val intent = Intent(this, activity_food::class.java)
            startActivity(intent)
        }
        val retrofitBuilder= Retrofit.Builder()
            .baseUrl("http://52.25.229.242:8000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val retrofitData=retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<Homepage?>
        {
            override fun onResponse(call: Call<Homepage?>, response: Response<Homepage?>) {
                var responseBody =response.body()
                val dataList= responseBody?.message!!

                val collectDataInStringBuilder=StringBuilder()

                for(myData in dataList) {
                    collectDataInStringBuilder.append(myData.titlecase() + " ")
                }
                val tv=findViewById<TextView>(R.id.textView2)
                tv.text=collectDataInStringBuilder

            }

            override fun onFailure(call: Call<Homepage?>, t: Throwable) {
                Log.d(TAG, "onFailure:" +t.message)

            }
        })


    }
}