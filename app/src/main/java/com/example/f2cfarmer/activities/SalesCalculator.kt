package com.example.f2cfarmer.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.f2cfarmer.R

class SalesCalculator : AppCompatActivity() {

    private lateinit var btnCalc : Button
    private lateinit var tvsales : TextView
    private lateinit var etIncome : EditText
    private lateinit var etPricePU : EditText
    private lateinit var tvAnswer : TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sales_calculator)

        btnCalc = findViewById(R.id.buttonCalc)
        tvsales = findViewById(R.id.textViewAnswer)
        tvAnswer = findViewById(R.id.textViewAnswer)
        etIncome = findViewById(R.id.editTextExcIncome)
        etPricePU = findViewById(R.id.etUnitPrice)

        // initializing values
        val num1 = etIncome
        val num2 = etPricePU

        val tot = tvAnswer


        btnCalc.setOnClickListener{

            //setting values taken from edit text to text views
            val val1 = num1.text.toString().toInt()
            val val2 = num2.text.toString().toInt()

            val result = val1/val2

            tot.text = result.toString()
        }
    }

}