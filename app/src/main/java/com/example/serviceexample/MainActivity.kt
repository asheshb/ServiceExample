package com.example.serviceexample

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
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

const val BROADCAST_ACTION_PLAY_STATUS = "BROADCAST_ACTION_PLAY_STATUS"
const val BROADCAST_ACTION_PLAY_MESSAGE = "BROADCAST_ACTION_PLAY_MESSAGE"

class MainActivity : AppCompatActivity() {

    val broadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(contxt: Context?, intent: Intent?) {
            when (intent?.action) {
                BROADCAST_ACTION_PLAY_STATUS -> play_status.text =
                    getString(R.string.play_status_text, intent.getStringExtra(BROADCAST_ACTION_PLAY_MESSAGE))
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        LocalBroadcastManager.getInstance(this)
            .registerReceiver(broadcastReceiver, IntentFilter(BROADCAST_ACTION_PLAY_STATUS))


        play_ringtone.setOnClickListener{
            ContextCompat.startForegroundService(this, Intent(this, MyService::class.java).apply {
                putExtra(PLAY_KEY, MusicPlay.RINGTONE.name)
            })
        }

        play_alarm.setOnClickListener{
            ContextCompat.startForegroundService(this, Intent(this, MyService::class.java).apply {
                putExtra(PLAY_KEY, MusicPlay.ALARM.name)
            })
        }

        stop_playing.setOnClickListener{
            ContextCompat.startForegroundService(this, Intent(this, MyService::class.java).apply {
                putExtra(ACTION_KEY, MusicAction.STOP.name)
            })
        }

        stop_service.setOnClickListener{
            stopService(Intent(this, MyService::class.java))
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        LocalBroadcastManager.getInstance(this)
            .unregisterReceiver(broadcastReceiver)
    }
}
