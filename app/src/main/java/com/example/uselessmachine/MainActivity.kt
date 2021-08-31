package com.example.uselessmachine

import android.content.IntentSender
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.Switch
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout



class MainActivity : AppCompatActivity() {

    lateinit var switchUseless: Switch
    lateinit var buttonSelfDestruct: Button
    lateinit var buttonLookBusy: Button
    lateinit var layoutMain: ConstraintLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wireWidgets()

        //google what the appropriate listener for a switch
        switchUseless.setOnCheckedChangeListener { buttonView, isChecked ->
            //toast for when it becomes checked
            if (isChecked) {
                Toast.makeText(this@MainActivity, "switch is on", Toast.LENGTH_SHORT).show()
                startThreeSecondSwitchTimer()
            }
            //different toast for when it is unchecked
            else {
                Toast.makeText(this@MainActivity, "switch is off", Toast.LENGTH_SHORT).show()

            }
        }

        //if a function implemented via lambda only has 1 parameter, that parameter by default is called "it"
        //you can still name it if you want... varName ->
        buttonSelfDestruct.setOnClickListener {
            startTenSecondSwitchTimer()
        }
    }

    //IDENTIFY THE WIDGETS
    private fun wireWidgets() {
        switchUseless = findViewById(R.id.switch_main_useless)
        buttonLookBusy = findViewById(R.id.button_main_lookBusy)
        buttonSelfDestruct = findViewById(R.id.button_main_selfDestruct)
        layoutMain = findViewById(R.id.layoutMain)
    }

    //google CountDownTimer class (goal: make it turn off after 3 seconds)
    //THREE SECOND TIMER
    private fun startThreeSecondSwitchTimer() {
        //make an anonymous inner class to create to
        val uselessTime = object :CountDownTimer(3000, 1000) {
            // callbacks -- functions that will be called later, sometime in the future

            override fun onTick(millisUntilFinished: Long) {
                if(!switchUseless.isChecked){
                    cancel()
                }
            }

            override fun onFinish() {
                //turn the switch off
                switchUseless.isChecked = false
            }
        }
        uselessTime.start()
    }

    //TEN SECOND FORCE QUIT TIMER
    private fun startTenSecondSwitchTimer() {
        val startTime = 10000
        val intervalTime = 1000
        val selfDistructTime = object :CountDownTimer(startTime.toLong(), intervalTime.toLong()) {
            // callbacks -- functions that will be called later, sometime in the future

            override fun onTick(millisUntilFinished: Long) {
                val seconds = (millisUntilFinished/1000) + 1
                //var lastFlashTime = 10000L
                //var flashDuration = 500
                //var isRed = false
                buttonSelfDestruct.text = "$seconds seconds remaining"
                if(seconds.toInt() % 2 == 0){
                    layoutMain.setBackgroundColor(Color.argb(100, 255, 0, 0))
                } else {
                    layoutMain.setBackgroundColor(Color.argb(100, 0, 255, 0))
                }
            }

            override fun onFinish() {
                //turn the switch off
                finish()
            }
        }
        selfDistructTime.start()
    }

}