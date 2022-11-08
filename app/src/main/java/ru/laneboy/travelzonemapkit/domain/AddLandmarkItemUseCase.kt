package ru.laneboy.travelzonemapkit.domain

class AddLandmarkItemUseCase(private val landmarkRepository: LandmarkRepository) {

    fun addLandmarkItem(landmarkItem: LandmarkItem) {
        landmarkRepository.addLandmarkItem(landmarkItem)
    }
}