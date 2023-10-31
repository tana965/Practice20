package com.example.practice20

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    lateinit var sensorManager: SensorManager
    lateinit var gyroscopeSensorListener: SensorEventListener
    fun Click(view: View){
        val tv: TextView = findViewById(R.id.textView)
        val tv2: TextView = findViewById(R.id.textView2)
        val tv3: TextView = findViewById(R.id.textView3)
        val btn: Button = findViewById(R.id.button)
        val btnStop: Button = findViewById(R.id.button2)
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        val gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)

        gyroscopeSensorListener = object : SensorEventListener {
            override fun onSensorChanged(sensorEvent: SensorEvent) {
                var x = sensorEvent.values[0].roundToInt().toDouble() / 100
                var y = sensorEvent.values[1].roundToInt().toDouble() / 100
                var z = sensorEvent.values[2].roundToInt().toDouble() / 100

                tv.setText("X ${x.toString()}")
                tv2.setText("Y ${y.toString()}")
                tv3.setText("Z ${z.toString()}")

            }
            override fun onAccuracyChanged(sensor: Sensor, i: Int) {}
        }
        sensorManager.registerListener(
            gyroscopeSensorListener,
            gyroscopeSensor, SensorManager.SENSOR_DELAY_UI
        )
        btn.isEnabled = false
        btnStop.isEnabled = true
    }

    fun btnStop(view: View){
        sensorManager.unregisterListener(gyroscopeSensorListener)
        val btn: Button = findViewById(R.id.button)
        val btnStop: Button = findViewById(R.id.button2)
        btn.isEnabled = true
        btnStop.isEnabled = false
    }

}