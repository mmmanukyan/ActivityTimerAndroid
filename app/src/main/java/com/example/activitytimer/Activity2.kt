package com.example.activitytimer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView

class Activity2 : AppCompatActivity() {
    private var timer : CountDownTimer? =null
    private var time:Long = 0
    private var txt:TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)
        time = intent.getLongExtra("time", time);
        txt = findViewById<TextView>(R.id.textView)
    }
    override fun onStart() {
        super.onStart()
        if (timer == null && time > 0) {
            timer = object : CountDownTimer(time, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    time = millisUntilFinished
                    txt?.text = (time/1000).toString()
                }
                override fun onFinish() {
                    time = 0;
                    txt?.text = "Finished"
                    timer = null
                }
            }.start()
        }
    }
    override fun onStop() {
        super.onStop()
        timer?.cancel()
        timer = null
    }
}