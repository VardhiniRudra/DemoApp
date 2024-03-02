package com.example.demoapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val data = intent.getParcelableExtra<MyDataItem>("Data")
        if(data!= null){
            val textView1: TextView = findViewById(R.id.detailActivityTV)
            val textView2: TextView = findViewById(R.id.detailActivityTV2)
            val textView3: TextView = findViewById(R.id.detailActivityTV3)
            val imageView: ImageView = findViewById(R.id.detailActivityIV)

            textView1.text = data.userId.toString()
            imageView.setImageResource(R.mipmap.ic_launcher)
            textView2.text = data.title
            textView3.text = data.body
        }
    }
}