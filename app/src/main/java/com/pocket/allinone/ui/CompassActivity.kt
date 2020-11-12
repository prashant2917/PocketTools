package com.pocket.allinone.ui

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import com.pocket.allinone.R
import kotlinx.android.synthetic.main.activity_compass.*
import kotlin.math.roundToInt


class CompassActivity : BaseActivity(),SensorEventListener {
    private  var currentDegree= 0f
    private lateinit var mSensorManager: SensorManager
    override fun getLayoutResource(): Int {
        return R.layout.activity_compass
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = resources.getString(R.string.compass)
        setHomeButtonEnabled(true)
       mSensorManager = getSystemService(Context.SENSOR_SERVICE) as  SensorManager

    }

    override fun onResume() {
        super.onResume()
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
            SensorManager.SENSOR_DELAY_GAME);
    }

    override fun onPause() {
        super.onPause()
        mSensorManager.unregisterListener(this)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    override fun onSensorChanged(event: SensorEvent?) {
        val degree = event!!.values[0].roundToInt().toFloat()

        tvHeading.text="Heading: " + java.lang.Float.toString(degree) + " degrees"

        // create a rotation animation (reverse turn degree degrees)

        // create a rotation animation (reverse turn degree degrees)
        val ra = RotateAnimation(
            currentDegree,
            -degree,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )

        // how long the animation will take place

        // how long the animation will take place
        ra.duration = 210

        // set the animation after the end of the reservation status

        // set the animation after the end of the reservation status
        ra.fillAfter = true

        // Start the animation

        // Start the animation
        img_compass.startAnimation(ra)
        currentDegree = -degree
    }
}