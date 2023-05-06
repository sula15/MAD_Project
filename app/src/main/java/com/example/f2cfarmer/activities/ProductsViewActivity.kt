package com.example.f2cfarmer.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.f2cfarmer.R
import com.example.f2cfarmer.adapters.PrdAdapter
import com.example.f2cfarmer.models.ProductsModel
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

class ProductsViewActivity : AppCompatActivity() {

    private lateinit var prdRecyclerView: RecyclerView
    private lateinit var tvLoadingData : TextView
    private lateinit var prdList: ArrayList<ProductsModel>
    private lateinit var dfRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products_view)

        prdRecyclerView = findViewById(R.id.rvPrd)
        prdRecyclerView.layoutManager = LinearLayoutManager(this)
        prdRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)

        prdList = arrayListOf<ProductsModel>()

        getProductsData()

    }

    private fun getProductsData(){
        prdRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        dfRef = FirebaseDatabase.getInstance().getReference("Products")

        dfRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                prdList.clear()
                if(snapshot.exists()){
                    for (prdSnap in snapshot.children){
                        val prdData = prdSnap.getValue(ProductsModel::class.java)
                        prdList.add(prdData!!)
                    }
                    val mAdapter = PrdAdapter(prdList)
                    prdRecyclerView.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object : PrdAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                           intent = Intent(this@ProductsViewActivity, ProductDetailsActivity::class.java)

                            //put extras

                            intent.putExtra("prdId", prdList[position].prdId)
                            intent.putExtra("prdName", prdList[position].prdName)
                            intent.putExtra("prdPrice", prdList[position].prdPrice)
                            intent.putExtra("prdQty", prdList[position].prdQuantity)
                            startActivity(intent)
                        }

                    })

                    prdRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}