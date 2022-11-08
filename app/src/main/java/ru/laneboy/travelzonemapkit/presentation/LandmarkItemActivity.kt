package ru.laneboy.travelzonemapkit.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.laneboy.travelzonemapkit.R

class LandmarkItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landmark_item)
    }

    companion object {

        fun newIntentAddItem(context: Context): Intent {
            return Intent(context, LandmarkItemActivity::class.java)
        }
    }
}