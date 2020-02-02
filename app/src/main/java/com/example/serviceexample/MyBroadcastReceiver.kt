package com.example.serviceexample

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        //runs on Main thread
        if (intent.action == Intent.ACTION_LOCALE_CHANGED) {
             Toast.makeText(context, Intent.ACTION_LOCALE_CHANGED, Toast.LENGTH_LONG).show()
         }
    }
}