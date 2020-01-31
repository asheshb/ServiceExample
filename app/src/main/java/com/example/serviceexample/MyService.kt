package com.example.serviceexample

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.os.IBinder
import android.provider.Settings


class MyService: Service(){


    private var player: MediaPlayer? = null

    //For bound service
    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()


    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        //service runs in Main Thread so for heavy work offload to a
        // coroutines or any other thread

        if(intent?.extras?.containsKey(ACTION_KEY) == true){
            val action = MusicAction.valueOf(intent.extras?.getString(ACTION_KEY)!!)
            if(action == MusicAction.STOP){
                player?.stop()
                // service can stop by itself
                //stopSelf()
            }
        } else if(intent?.extras?.containsKey(PLAY_KEY) == true) {
            val play = MusicPlay.valueOf(intent.extras?.getString(PLAY_KEY)!!)
            playMusic(play)
        }

        //Restart the activity if system kills it for some reason
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()

        player?.stop()
        player?.release()
    }

    private fun playMusic(action: MusicPlay){
        val uri = when(action){
            MusicPlay.ALARM -> {
                Settings.System.DEFAULT_ALARM_ALERT_URI
            }
            else -> {
                Settings.System.DEFAULT_RINGTONE_URI
            }
        }
        if(player?.isPlaying == true){
            player?.stop()
        }

        player = MediaPlayer.create(this,
                uri)
        player?.isLooping = true
        player?.start()


    }
}