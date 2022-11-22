package ru.laneboy.travelzonemapkit.domain

import androidx.annotation.DrawableRes
import androidx.annotation.RawRes

data class LandmarkItem(
    val name: String,
    val description: String,

    @DrawableRes
    val landmarkImage: Int,

    @RawRes
    val landmarkSound: Int,
    var id: Int = UNDEFINED_ID
) {

    companion object {
        const val UNDEFINED_ID = -1
    }
}
