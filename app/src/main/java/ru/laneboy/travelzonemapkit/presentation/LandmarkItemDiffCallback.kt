package ru.laneboy.travelzonemapkit.presentation

import androidx.recyclerview.widget.DiffUtil
import ru.laneboy.travelzonemapkit.domain.LandmarkItem

class LandmarkItemDiffCallback: DiffUtil.ItemCallback<LandmarkItem>() {

    override fun areItemsTheSame(oldItem: LandmarkItem, newItem: LandmarkItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: LandmarkItem, newItem: LandmarkItem): Boolean {
        return oldItem == newItem
    }

}