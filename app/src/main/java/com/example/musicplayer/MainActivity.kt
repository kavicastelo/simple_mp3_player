package com.example.musicplayer

import kotlinx.android.synthetic.main.activity_main.*
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.SeekBar

class MainActivity : AppCompatActivity() {

    lateinit var runnable: Runnable
    private var handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mediaPlayer :MediaPlayer = MediaPlayer.create(this,R.raw.music1)

        seekBar.progress = 0
        seekBar.max = mediaPlayer.duration

        btn_play.setOnClickListener {
            if (!mediaPlayer.isPlaying){
                mediaPlayer.start()
                btn_play.setImageResource(R.drawable.ic_baseline_pause_24)
            }
            else{
                mediaPlayer.pause()
                btn_play.setImageResource(R.drawable.ic_baseline_play_arrow_24)
            }
        }

        seekBar.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, pos: Int, changed: Boolean) {
                if(changed){
                    mediaPlayer.seekTo(pos)
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })

        runnable = Runnable {
            seekBar.progress = mediaPlayer.currentPosition
            handler.postDelayed(runnable,1000)
        }
        handler.postDelayed(runnable,1000)

        mediaPlayer.setOnCompletionListener {
            btn_play.setImageResource(R.drawable.ic_baseline_play_arrow_24)
            seekBar.progress = 0
        }
    }
}