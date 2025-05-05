package com.example.project1

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LastActivity : AppCompatActivity() {
    private var flower = ""
    private var description = ""
    private var price = ""
    private var pos = 0
    private lateinit var itemPrice: TextView
    private lateinit var itemDetail: TextView
    private lateinit var itemTitle: TextView
    private lateinit var item_image: ImageView
    private lateinit var btnReturn: Button
    private var images = intArrayOf(R.drawable.dahlia, R.drawable.daisy, R.drawable.freesia, R.drawable.lilac, R.drawable.lily,
        R.drawable.marigold, R.drawable.rose, R.drawable.peony, R.drawable.sunflower, R.drawable.tulip)
    private lateinit var tvTitle: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_last)
        initViews()
        getDataFromBundle()
        btnReturnPress()
    }

    private fun initViews() {
        //itemImage = findViewById(R.id.item_image)
        itemTitle = findViewById(R.id.item_title)
        itemDetail = findViewById(R.id.item_detail)
        itemPrice = findViewById(R.id.item_price)
        item_image = findViewById(R.id.item_image)
        btnReturn = findViewById(R.id.btnReturn)
    }

    private fun getDataFromBundle() {
        flower = intent.extras?.getString("flower", "0") ?: "0"
        description = intent.extras?.getString("description", "0") ?: "0"
        price = intent.extras?.getString("price", "0") ?: "0"
        pos = intent.extras?.getInt("pos", 0) ?: 0
        itemTitle.setText(flower.toString())
        itemDetail.setText(description.toString())
        itemPrice.setText(price.toString())
        item_image.setImageResource(images[pos])
    }

    private fun btnReturnPress() {
        btnReturn.setOnClickListener() {
            finish()
        }
    }
}