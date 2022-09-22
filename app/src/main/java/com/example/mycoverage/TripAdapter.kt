package com.example.mycoverage

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

private const val TAG = "TripAdapter"

class TripAdapter(val activity: Activity, private val trip: List<Trip>) :
    RecyclerView.Adapter<TripAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_trip, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val choice = trip[position]
        holder.bind(choice)
    }

    override fun getItemCount(): Int {
        return trip.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val stepsText: TextView
        private val locationText: TextView
        private val dateText: TextView
        private val cardView: CardView

        fun bind(trip: Trip) {
            stepsText.text = "Steps: " + trip.steps
            locationText.text = "Location: " + trip.location
            dateText.text = "Date: " + trip.date

        }

        init {
            stepsText = itemView.findViewById(R.id.item_steps)
            locationText = itemView.findViewById(R.id.item_location)
            dateText = itemView.findViewById(R.id.item_date)
            cardView = itemView.findViewById(R.id.cardview)
        }
    }
}

