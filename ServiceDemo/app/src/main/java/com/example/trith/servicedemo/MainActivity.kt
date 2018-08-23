package com.example.trith.servicedemo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent = Intent(applicationContext,SensorService::class.java)
        startService(intent)
        val intentFilter = IntentFilter(BroadcastManager.ACTION_STEP_COUNT)
        registerReceiver(mStepCountReciever,intentFilter)
    }

    val mStepCountReciever = object : BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            val step = p1!!.extras.getInt(BroadcastManager.ACTION_STEP_COUNT)
            tvCurrentStep.text = step.toString()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(mStepCountReciever)
    }
}
