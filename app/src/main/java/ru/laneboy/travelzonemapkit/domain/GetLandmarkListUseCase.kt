package ru.laneboy.travelzonemapkit.domain

import androidx.lifecycle.LiveData

class GetLandmarkListUseCase(private val landmarkRepository: LandmarkRepository) {

    fun getLandmarkList(): LiveData<List<LandmarkItem>> {
        return landmarkRepository.getLandmarkList()
    }
}