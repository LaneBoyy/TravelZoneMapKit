package ru.laneboy.travelzonemapkit.domain

import androidx.lifecycle.LiveData

interface LandmarkRepository {

    fun addLandmarkItem(landmarkItem: LandmarkItem)

    fun getLandmarkItem(landmarkId: Int): LandmarkItem

    fun getLandmarkList(): LiveData<List<LandmarkItem>>
}