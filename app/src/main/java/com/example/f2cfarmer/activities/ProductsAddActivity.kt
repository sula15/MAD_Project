package com.example.f2cfarmer.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.f2cfarmer.models.ProductsModel
import com.example.f2cfarmer.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ProductsAddActivity : AppCompatActivity() {

    private lateinit var etPrdName : EditText
    private lateinit var etPrdPrice : EditText
    //private lateinit var etPrdDescription : EditText
    private lateinit var etPrdQuantity : EditText
    private lateinit var btnSave : Button

    private lateinit var dbRef : DatabaseReference



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products_add)

        //initializing the views
        etPrdName = findViewById(R.id.etPrdName)
        etPrdPrice = findViewById(R.id.etPrdPrice)
        etPrdQuantity = findViewById(R.id.etPrdQty)
        btnSave = findViewById(R.id.btnSave)

        dbRef = FirebaseDatabase.getInstance().getReference("Products")

        btnSave.setOnClickListener {
            saveProductData()
        }


    }

    private fun saveProductData() {
        //getting values

        val prdName = etPrdName.text.toString()
        val prdPrice = etPrdPrice.text.toString()
        val prdQuantity = etPrdQuantity.text.toString()

        //validate the form
        if(prdName.isEmpty()){
            etPrdName.error = "Please enter product Name"
            return
        }
        if(prdPrice.isEmpty()){
            etPrdPrice.error = "Please enter product Price"
            return
        }

        if(prdQuantity.isEmpty()){
            etPrdQuantity.error = "Please enter product Quantity"
            return
        }

        val prdId= dbRef.push().key!!

        val product = ProductsModel(prdId, prdName, prdPrice, prdQuantity)

        dbRef.child(prdId).setValue(product)
            .addOnCompleteListener {
                Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()
            }.addOnFailureListener{ err->
                Toast.makeText(this, "Error${err.message}", Toast.LENGTH_LONG).show()
            }

    }
}