package ru.laneboy.travelzonemapkit.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.laneboy.travelzonemapkit.R
import ru.laneboy.travelzonemapkit.domain.LandmarkItem

class LandmarkListAdapter : ListAdapter<LandmarkItem, LandmarkItemViewHolder>(LandmarkItemDiffCallback()) {

    var onLandmarkItemClickListener: ((LandmarkItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LandmarkItemViewHolder {
        val layout = R.layout.landmark_item
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return LandmarkItemViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: LandmarkItemViewHolder, position: Int) {
        val landmarkItem = getItem(position)
        with(viewHolder) {
            ivImage.setImageResource(landmarkItem.landmarkImage)
            tvTitle.text = landmarkItem.name
            tvDescription.text = landmarkItem.description
            view.setOnClickListener {
                onLandmarkItemClickListener?.invoke(landmarkItem)
            }
        }
    }

    companion object {

        const val VIEW_TYPE = 100
        const val MAX_POOL_SIZE = 5
    }
}