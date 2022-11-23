package ru.laneboy.travelzonemapkit.presentation.activities

import android.media.MediaPlayer
import android.os.Bundle
import android.view.WindowManager
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.appcompat.app.AppCompatActivity
import ru.laneboy.travelzonemapkit.R
import ru.laneboy.travelzonemapkit.databinding.ActivityLandmarkItemBinding
import ru.laneboy.travelzonemapkit.presentation.FragmentBottomSheet
import ru.laneboy.travelzonemapkit.presentation.FragmentBottomSheet.Companion.INTENT_DESCRIPTION
import ru.laneboy.travelzonemapkit.presentation.FragmentBottomSheet.Companion.INTENT_TITLE

class LandmarkItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLandmarkItemBinding
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLandmarkItemBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        removeStatusBar()
        setTexts()
        setImageAndSound()
        onClickExit()
        activateSeekBarTime()
        onClickButtonPlay()


    }

    private fun removeStatusBar() {
        val w = window
        w.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }

    private fun setTexts() {
        binding.tvTitle.text = intent.getStringExtra(INTENT_TITLE)
        binding.tvDescription.text = intent.getStringExtra(INTENT_DESCRIPTION)
        binding.tvStartTime.text = "0:00"
    }

    private fun setImageAndSound() {
        val bundle = intent.extras
        if (bundle != null) {
            binding.imageViewBigPicture
                .setImageResource(bundle.getInt(FragmentBottomSheet.INTENT_PICTURE))

            mediaPlayer = MediaPlayer
                .create(this, bundle.getInt(FragmentBottomSheet.INTENT_SOUND))
            mediaPlayer.isLooping
            mediaPlayer.seekTo(0)
            binding.tvEndTime.text = millisecondsToString(mediaPlayer.duration)
        }
    }

    private fun onClickExit() {
        binding.btnExitFromDescription.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    //AUDIO FUNCTIONS ↓

    private fun activateSeekBarTime() {
        binding.sbTime.max = mediaPlayer.duration
        binding.sbTime.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, isFromUser: Boolean) {
                if (isFromUser) {
                    mediaPlayer.seekTo(progress)
                    seekBar.progress = progress
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {

            }
        })
        val thread = Thread {
            while (mediaPlayer != null) {
                if (mediaPlayer.isPlaying) {
                    try {
                        val current = mediaPlayer.currentPosition.toDouble()
                        val elapsedTime = millisecondsToString(current.toInt())
                        runOnUiThread {
                            binding.tvStartTime.text = elapsedTime
                            binding.sbTime.progress = current.toInt()
                        }
                        Thread.sleep(1000)
                    } catch (_: InterruptedException) {
                    }
                }
            }
        }
        thread.start()
    }

    private fun onClickButtonPlay() {
        binding.btnPlay.setOnClickListener {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
                binding.btnPlay.setBackgroundResource(R.drawable.ic_btn_play)
            } else {
                mediaPlayer.start()
                binding.btnPlay.setBackgroundResource(R.drawable.ic_btn_pause)
            }
        }
    }

    private fun millisecondsToString(time: Int): String {
        var elapsedTime = ""
        val minutes = time / 1000 / 60
        val seconds = time / 1000 % 60
        elapsedTime = "$minutes:"
        if (seconds < 10) {
            elapsedTime += "0"
        }
        elapsedTime += seconds
        return elapsedTime
    }
}
