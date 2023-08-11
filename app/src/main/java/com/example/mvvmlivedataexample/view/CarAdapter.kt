package com.example.mvvmlivedataexample.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmlivedataexample.R
import com.example.mvvmlivedataexample.model.Car
import com.example.mvvmlivedataexample.utils.Constants.API_BASE_URL
import kotlinx.android.synthetic.main.car_item_view.view.*
import java.util.*

class CarAdapter (private var cars: List<Car>) :RecyclerView.Adapter<CarAdapter.MViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.car_item_view, parent, false)
        return MViewHolder(view)
    }

    override fun onBindViewHolder(vh: MViewHolder, position: Int) {
        //render
        vh.bind(cars[position])
    }

    override fun getItemCount(): Int {
        return cars.size
    }

    fun update(data: List<Car>) {
        cars = data
        notifyDataSetChanged()
    }

    class MViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textViewName: TextView = view.tvItemTitle
        private val textViewPrice: TextView = view.tvItemPrice
        private val imageView: AppCompatImageView = view.ivItemImage

        fun bind(Car: Car) {
            textViewName.text = String.format(Locale.getDefault(),"%s %s %s",Car.year,Car.make.manufacturer,Car.make.model)
            textViewPrice.text = String.format(Locale.getDefault(),"$%s",Car.price)
            Glide.with(imageView.context).load(API_BASE_URL+"/"+Car.image).placeholder(R.drawable.ic_placeholder).error(R.drawable.ic_error).into(imageView)
        }
    }
}
