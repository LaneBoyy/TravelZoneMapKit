package ru.laneboy.travelzonemapkit.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import ru.laneboy.travelzonemapkit.databinding.ActivityLandmarkItemBinding
import ru.laneboy.travelzonemapkit.presentation.FragmentBottomSheetTest.Companion.INTENT_DESCRIPTION
import ru.laneboy.travelzonemapkit.presentation.FragmentBottomSheetTest.Companion.INTENT_TITLE

class LandmarkItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLandmarkItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLandmarkItemBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Убираем статус бар
        val w = window
        w.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )



        binding.tvTitle.text = intent.getStringExtra(INTENT_TITLE)
        binding.tvDescription.text = intent.getStringExtra(INTENT_DESCRIPTION)

        onClickExit()
    }

    private fun onClickExit() {
        binding.btnExitFromDescription.setOnClickListener {
            onBackPressed()
        }
    }
}