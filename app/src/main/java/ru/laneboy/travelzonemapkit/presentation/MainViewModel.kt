package ru.laneboy.travelzonemapkit.presentation

import androidx.lifecycle.ViewModel
import ru.laneboy.travelzonemapkit.data.LandmarkRepositoryImpl
import ru.laneboy.travelzonemapkit.domain.GetLandmarkListUseCase

class MainViewModel : ViewModel() {

    private val repository = LandmarkRepositoryImpl

    private val getLandmarkListUseCase = GetLandmarkListUseCase(repository)

    val landmarkList = getLandmarkListUseCase.getLandmarkList()
}