package com.example.activitytimer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var timer :CountDownTimer? =null
    private var time:Long = 0
    private var txt:TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txt = findViewById<TextView>(R.id.textView)
    }
    override fun onStop() {
        super.onStop()
        stopTimer()
    }
    override fun onRestart() {
        super.onRestart()
        startTimer()
    }
    fun startTimer( view: View?=null) {
        stopTimer()
        time = 100000
        startTimer()
    }
    fun nextPage(view: View){
        Intent(this, Activity2::class.java).run {
            putExtra("time", time)
            startActivity(this)
        }
    }
    private fun startTimer(){
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
    private fun stopTimer(){
        timer?.cancel()
        timer = null
    }
}