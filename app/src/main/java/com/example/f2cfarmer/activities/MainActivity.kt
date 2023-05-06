package com.example.f2cfarmer.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.f2cfarmer.R

class MainActivity : AppCompatActivity() {

    private lateinit var btnAddProducts : Button
    private lateinit var btnViewProducts : Button
    private lateinit var btnSalesCalc : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnAddProducts = findViewById(R.id.btnAddProducts)
        btnViewProducts = findViewById(R.id.btnViewProducts)
        btnSalesCalc = findViewById(R.id.btnSalesCalculator)


        btnAddProducts.setOnClickListener {
            val intent = Intent(this, ProductsAddActivity::class.java)
            startActivity(intent)
        }

        btnViewProducts.setOnClickListener {
            val intent = Intent(this, ProductsViewActivity::class.java)
            startActivity(intent)
        }

        btnSalesCalc.setOnClickListener {
            val intent = Intent(this, SalesCalculator::class.java)
            startActivity(intent)
        }

    }
}