package ru.laneboy.travelzonemapkit.presentation

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.laneboy.travelzonemapkit.R

class LandmarkItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    val ivImage = view.findViewById<ImageView>(R.id.iv_image)
    val tvTitle = view.findViewById<TextView>(R.id.tv_title)
    val tvDescription = view.findViewById<TextView>(R.id.tv_description)
}