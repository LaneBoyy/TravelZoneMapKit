package ru.laneboy.travelzonemapkit.domain

import androidx.annotation.DrawableRes

data class LandmarkItem(
    val name: String,
    val description: String,
    @DrawableRes
    val landmark_image: Int,
    var id: Int = UNDEFINED_ID
) {

    companion object {
        const val UNDEFINED_ID = -1
    }
}