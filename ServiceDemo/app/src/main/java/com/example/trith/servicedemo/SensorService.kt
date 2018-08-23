package com.example.trith.servicedemo

import android.app.Service
import android.arch.persistence.room.Room
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.IBinder
import android.util.Log
import com.example.trith.servicedemo.db.StepCounterDb
import com.example.trith.servicedemo.db.entity.StepAllDay
import com.example.trith.servicedemo.db.entity.StepInDay
import java.util.*

/**
 * Created by tri.th on 22/08/2018.
 */
class SensorService : Service(), StepListener {
    private val TAG = "SensorService"
    private val TIME_INTERVAL = 5 * 60 * 1000
    private lateinit var mStepCountPref: SharedPreferences
    private lateinit var stepDetector: StepDetector
    private var mStepCount: Int = 0
    private lateinit var mStepCountDb: StepCounterDb

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "-----------onStartCommand------------")
        mStepCountDb = Room.databaseBuilder(applicationContext, StepCounterDb::class.java, "stepcounter.db")
                .fallbackToDestructiveMigration()
                .build()
        mStepCountPref = getSharedPreferences(getString(R.string.pref_step_count_name), Context.MODE_PRIVATE)
        mStepCount = mStepCountPref.getInt(getString(R.string.pref_step_count_last_step), 0)
        val lastTime = mStepCountPref.getLong(getString(R.string.pref_step_count_last_step_day), 0)
        resetStepIfNeeded(lastTime)
        sendCurrentStep(mStepCount)
        registerSensorListener(0, Sensor.TYPE_ACCELEROMETER)
        return START_STICKY
    }

    private fun registerSensorListener(maxdelay: Int, sensorType: Int) {
        Log.d(TAG, "registerSensorListener")
        stepDetector = StepDetector()
        stepDetector.registerListener(this)
        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val sensor = sensorManager.getDefaultSensor(sensorType)
        sensorManager.registerListener(stepDetector, sensor, SensorManager.SENSOR_DELAY_FASTEST)
    }

    private fun unregisterSensorListener() {
        Log.d(TAG, "unregisterSensorListener")
        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensorManager.unregisterListener(stepDetector)
    }

    override fun step(timeNs: Long) {
        mStepCount++
        sendCurrentStep(mStepCount)

        val lastTime = mStepCountPref.getLong(getString(R.string.pref_step_count_last_step_day), 0)
        val lastStep = mStepCountPref.getInt(getString(R.string.pref_step_count_last_step), 0)

        val isNewDate = resetStepIfNeeded(lastTime)

        if (isShouldSaveToDatabase(lastTime)) {
            // save current step to StepInDay table
            val step = StepInDay(mStepCount,Date().time)
            mStepCountDb.StepDao().insertStepInDay(step)
        }

        if (isNewDate) {
            // save last step to StepEveryDate table
            val step = StepAllDay(lastStep,lastTime,"text")
            mStepCountDb.StepDao().insertStepAllDay(step)
        }
        //save last step and date
        with(mStepCountPref.edit()) {
            putInt(getString(R.string.pref_step_count_last_step), mStepCount)
            putLong(getString(R.string.pref_step_count_last_step_day), Date().time)
            commit()
        }
    }

    private fun isShouldSaveToDatabase(lastTime: Long): Boolean {
        return ((Date().time - lastTime) >= TIME_INTERVAL)
    }

    /**
     * This method will compare last step's date and current date if
     * # is the same -> return false
     * # is difference -> return true and reset step count
     */
    private fun resetStepIfNeeded(lastTime: Long): Boolean {
        val time = if (lastTime == 0L) Date().time else lastTime
        val lastStepDate = Date(time)
        val currentDate = Date()
        if (!currentDate.isTheSameDateWith(lastStepDate)) {
            mStepCount = 0
            return true
        }
        return false
    }

    private fun sendCurrentStep(step: Int) {
        val intent = Intent()
        intent.action = BroadcastManager.ACTION_STEP_COUNT
        intent.putExtra(BroadcastManager.ACTION_STEP_COUNT, step)
        sendBroadcast(intent)
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        unregisterSensorListener()
        super.onDestroy()
        Log.d(TAG, "------------onDestroy----------")
    }
}