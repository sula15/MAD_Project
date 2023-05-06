package com.example.f2cfarmer.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.f2cfarmer.R
import com.example.f2cfarmer.models.ProductsModel

class PrdAdapter(private val prdList: ArrayList<ProductsModel>) : RecyclerView.Adapter<PrdAdapter.ViewHolder>() {


        private lateinit var mListener: onItemClickListener

        //To Drag the click on the item
        interface onItemClickListener {
                fun onItemClick(position: Int)
        }
        fun setOnItemClickListener(clickListener: onItemClickListener){
                mListener = clickListener
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
               val itemView = LayoutInflater.from(parent.context).inflate(R.layout.product_list_rows,parent,false)
                return ViewHolder(itemView, mListener)
        }


        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
                val currentProduct = prdList[position]
                holder.tvRowsName.text = currentProduct.prdName

        }

        override fun getItemCount(): Int {
                return prdList.size
        }

        class ViewHolder(itemView: View , clickListener: onItemClickListener ) : RecyclerView.ViewHolder(itemView) {

                val tvRowsName : TextView = itemView.findViewById(R.id.tvRowsName)

                init{
                        itemView.setOnClickListener{
                                clickListener.onItemClick(adapterPosition)
                        }
                }
        }


}