package com.example.serviceexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

const val ACTION_KEY = "ACTION_KEY"
enum class MusicAction{
    STOP
}

const val PLAY_KEY = "PLAY_KEY"
enum class MusicPlay{
    RINGTONE,
    ALARM
}
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startService(Intent(this, MyService::class.java))

        play_ringtone.setOnClickListener{
            startService(Intent(this, MyService::class.java).apply {
                putExtra(PLAY_KEY, MusicPlay.RINGTONE.name)
            })
        }

        play_alarm.setOnClickListener{
            startService(Intent(this, MyService::class.java).apply {
                putExtra(PLAY_KEY, MusicPlay.ALARM.name)
            })
        }

        stop_playing.setOnClickListener{
            startService(Intent(this, MyService::class.java).apply {
                putExtra(ACTION_KEY, MusicAction.STOP.name)
            })
        }

        stop_service.setOnClickListener{
            stopService(Intent(this, MyService::class.java))
        }
    }
}
