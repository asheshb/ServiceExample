package com.example.serviceexample

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

private const val TAG = "MyBroadcastReceiver"

class MyBroadcastReceiver : BroadcastReceiver() {
    private var pendingResult: PendingResult? = null

    override fun onReceive(context: Context, intent: Intent) {
        if(intent.action == Intent.ACTION_LOCALE_CHANGED){
            //runs on main thread
            Toast.makeText(context, Intent.ACTION_LOCALE_CHANGED, Toast.LENGTH_LONG).show()
            //
        } else if(intent.action == "com.example.serviceexample.PLAY_STATUS") {
            Toast.makeText(context, "com.example.serviceexample.PLAY_STATUS", Toast.LENGTH_LONG).show()
        }
    }

    //region
    //    override fun onReceive(context: Context, intent: Intent) {
//        if(intent.action == Intent.ACTION_LOCALE_CHANGED){
//           GlobalScope.launch {
//               showToast(context)
//           }
//        }
//        //if need more time to finish. Still we are expected to finish under 10 seconds
//        pendingResult = goAsync()
//    }
//
//    private suspend fun showToast(context: Context){
//        withContext(Dispatchers.Main){
//            Toast.makeText(context, Intent.ACTION_LOCALE_CHANGED, Toast.LENGTH_LONG).show()
//        }
//
//        pendingResult?.finish()
//    }
    //endregion
}