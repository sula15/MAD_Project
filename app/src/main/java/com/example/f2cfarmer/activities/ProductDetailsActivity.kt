package com.example.f2cfarmer.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.f2cfarmer.R
import com.example.f2cfarmer.models.ProductsModel
import com.google.firebase.database.FirebaseDatabase

class ProductDetailsActivity : AppCompatActivity() {

    private lateinit var tvPrdId : TextView
    private lateinit var tvPrdName : TextView
    private lateinit var tvPrdPrice : TextView
    private lateinit var tvPrdQty : TextView
    private lateinit var buttonUpdate : Button
    private lateinit var buttonDelete : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)


        initView()
        setValuesToViews()

        buttonUpdate.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("prdId").toString(),
                intent.getStringExtra("PrdName").toString()
            )
        }

        buttonDelete.setOnClickListener {
            deleteRecord(
                intent.getStringExtra("prdId").toString()
            )
        }

    }

    private fun initView() {

        tvPrdId = findViewById(R.id.tvPrdId)
        tvPrdName= findViewById(R.id.tvPrdName)
        tvPrdPrice = findViewById(R.id.tvPrdPrice)
        tvPrdQty = findViewById(R.id.tvPrdQty)


        buttonUpdate = findViewById(R.id.buttonUpdate)
        buttonDelete = findViewById(R.id.buttonDelete)

    }

    //Setting values to the views
    private fun setValuesToViews(){

        tvPrdId.text = intent.getStringExtra("prdId")
        tvPrdName.text = intent.getStringExtra("prdName")
        tvPrdPrice.text = intent.getStringExtra("prdPrice")
        tvPrdQty.text = intent.getStringExtra("prdQty")

    }


    //Dialog builder creation
    private fun openUpdateDialog(
        prdId : String,
        prdName : String,

    ){
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.update_prddialog,null)

        mDialog.setView(mDialogView)

        val etPrdName = mDialogView.findViewById<EditText>(R.id.etPrdName)
        val etPrdPrice = mDialogView.findViewById<EditText>(R.id.etPrdPrice)
        val etPrdQty = mDialogView.findViewById<EditText>(R.id.etPrdQty)

        val btnUpdatePrdData = mDialogView.findViewById<Button>(R.id.btnUpdatePrdData)

        etPrdName.setText(intent.getStringExtra("prdName").toString())
        etPrdPrice.setText(intent.getStringExtra("prdPrice").toString())
        etPrdQty.setText(intent.getStringExtra("prdQty").toString())

        mDialog.setTitle("Updating $prdName Record")

        val alertDialog = mDialog.create()
        alertDialog.show()


        btnUpdatePrdData.setOnClickListener {
            updatePrdDate(
                prdId,
                etPrdName.text.toString(),
                etPrdPrice.text.toString(),
                etPrdQty.text.toString()
            )


            Toast.makeText(applicationContext, "Product Data Updated", Toast.LENGTH_LONG).show()

            //setting updated data to text views
            tvPrdName.text = etPrdName.text.toString()
            tvPrdPrice.text = etPrdPrice.text.toString()
            tvPrdQty.text = etPrdQty.text.toString()

            alertDialog.dismiss()
        }

    }



    //updating data in the database
    private fun updatePrdDate(
        id : String,
        name : String,
        price: String,
        qty : String

    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Products").child(id)
        val prdInfo = ProductsModel(id,name,price,qty)
        dbRef.setValue(prdInfo)
    }


    //Deleting Data
    private fun deleteRecord(
        id: String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Products").child(id)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Product data deleted", Toast.LENGTH_LONG).show()

            val intent = Intent(this, ProductsViewActivity::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{ error ->
            Toast.makeText(this, "Deleting Err ${error.message}", Toast.LENGTH_LONG).show()
        }
    }
}