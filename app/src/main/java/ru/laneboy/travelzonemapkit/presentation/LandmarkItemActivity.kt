package ru.laneboy.travelzonemapkit.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import ru.laneboy.travelzonemapkit.databinding.ActivityLandmarkItemBinding
import ru.laneboy.travelzonemapkit.presentation.FragmentBottomSheet.Companion.INTENT_DESCRIPTION
import ru.laneboy.travelzonemapkit.presentation.FragmentBottomSheet.Companion.INTENT_TITLE

class LandmarkItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLandmarkItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLandmarkItemBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        removeStatusBar()
        setTexts()
        setImage()
        setClickOnExit()
    }

    private fun setClickOnExit() {
        binding.btnExitFromDescription.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun setTexts() {
        binding.tvTitle.text = intent.getStringExtra(INTENT_TITLE)
        binding.tvDescription.text = intent.getStringExtra(INTENT_DESCRIPTION)
    }

    private fun setImage() {
        val bundle = intent.extras
        if (bundle != null) {
            binding.imageViewBigPicture
                .setImageResource(bundle.getInt(FragmentBottomSheet.INTENT_PICTURE))
        }
    }

    private fun removeStatusBar() {
        val w = window
        w.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }
}