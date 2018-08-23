package com.example.trith.servicedemo

import android.hardware.Sensor
import android.hardware.SensorEventListener
import android.hardware.Sensor.TYPE_ACCELEROMETER
import android.hardware.Sensor.TYPE_ORIENTATION
import android.hardware.SensorEvent
import android.hardware.SensorManager
import android.util.Log


/**
 * Created by tri.th on 22/08/2018.
 */
class StepDetectorVer2 : SensorEventListener {
    private val TAG = "StepDetector"
    private var mLimit = 15f
    private val mLastValues = FloatArray(3 * 2)
    private val mScale = FloatArray(2)
    private var mYOffset: Float = 0f

    private val mLastDirections = FloatArray(3 * 2)
    private val mLastExtremes = arrayOf(FloatArray(3 * 2), FloatArray(3 * 2))
    private val mLastDiff = FloatArray(3 * 2)
    private var mLastMatch = -1

    private var mStepListeners:StepListener? = null

    init {
        val h = 480 // TODO: remove this constant
        mYOffset = h * 0.5f
        mScale[0] = -(h.toFloat() * 0.5f * (1.0f / (SensorManager.STANDARD_GRAVITY * 2)))
        mScale[1] = -(h.toFloat() * 0.5f * (1.0f / SensorManager.MAGNETIC_FIELD_EARTH_MAX))
    }

    fun setSensitivity(sensitivity: Float) {
        mLimit = sensitivity // 1.97  2.96  4.44  6.66  10.00  15.00  22.50  33.75  50.62
    }

    fun registerListener(sl: StepListener) {
        mStepListeners = sl
    }

    //public void onSensorChanged(int sensor, float[] values) {
    override fun onSensorChanged(event: SensorEvent) {
        val sensor = event.sensor
        synchronized(this) {
            if (sensor.type === Sensor.TYPE_ORIENTATION) {
            } else {
                val j = if (sensor.type === Sensor.TYPE_ACCELEROMETER) 1 else 0
                if (j == 1) {
                    var vSum = 0f
                    for (i in 0..2) {
                        val v = mYOffset + event.values[i] * mScale[j]
                        vSum += v
                    }
                    val k = 0
                    val v = vSum / 3

                    val direction = (if (v > mLastValues[k]) 1 else if (v < mLastValues[k]) -1 else 0).toFloat()
                    if (direction == -mLastDirections[k]) {
                        // Direction changed
                        val extType = if (direction > 0) 0 else 1 // minumum or maximum?
                        mLastExtremes[extType][k] = mLastValues[k]
                        val diff = Math.abs(mLastExtremes[extType][k] - mLastExtremes[1 - extType][k])

                        if (diff > mLimit) {

                            val isAlmostAsLargeAsPrevious = diff > mLastDiff[k] * 2 / 3
                            val isPreviousLargeEnough = mLastDiff[k] > diff / 3
                            val isNotContra = mLastMatch != 1 - extType

                            if (isAlmostAsLargeAsPrevious && isPreviousLargeEnough && isNotContra) {
                                Log.i(TAG, "step")
                                mStepListeners?.step(event.timestamp)
                                mLastMatch = extType
                            } else {
                                mLastMatch = -1
                            }
                        }
                        mLastDiff[k] = diff
                    }
                    mLastDirections[k] = direction
                    mLastValues[k] = v
                }
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
    }

}