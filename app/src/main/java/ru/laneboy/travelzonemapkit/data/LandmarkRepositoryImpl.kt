package ru.laneboy.travelzonemapkit.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.laneboy.travelzonemapkit.R
import ru.laneboy.travelzonemapkit.domain.LandmarkItem
import ru.laneboy.travelzonemapkit.domain.LandmarkRepository

object LandmarkRepositoryImpl : LandmarkRepository {

    private val landmarkListLD = MutableLiveData<List<LandmarkItem>>()
    private val landmarkList = sortedSetOf<LandmarkItem>({ p0, p1 -> p0.id.compareTo(p1.id) })

    private var autoIncrementId = 0

    init {
        for (i in 0 until 7) {
            val item = LandmarkItem("Верблюд $i", "Он каменный", R.drawable.landmark_test)
            addLandmarkItem(item)
            updateList()
        }
    }

    override fun addLandmarkItem(landmarkItem: LandmarkItem) {
        if (landmarkItem.id == LandmarkItem.UNDEFINED_ID) {
            landmarkItem.id = autoIncrementId++
        }
        landmarkList.add(landmarkItem)
    }

    override fun getLandmarkItem(landmarkId: Int): LandmarkItem {
        return landmarkList.find {
            it.id == landmarkId
        } ?: throw RuntimeException("Element with id $landmarkId not found")
    }

    override fun getLandmarkList(): LiveData<List<LandmarkItem>> {
        return landmarkListLD
    }

    private fun updateList() {
        landmarkListLD.value = landmarkList.toList()
    }
}