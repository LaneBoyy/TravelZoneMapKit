package ru.laneboy.travelzonemapkit.domain

class GetLandmarkItemUseCase(private val landmarkRepository: LandmarkRepository) {

    fun getLandmarkItem(landmarkItemId: Int): LandmarkItem {
        return landmarkRepository.getLandmarkItem(landmarkItemId)
    }
}