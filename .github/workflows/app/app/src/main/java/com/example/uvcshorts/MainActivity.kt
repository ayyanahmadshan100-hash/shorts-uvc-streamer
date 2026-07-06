package com.example.uvcshorts

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.Gravity
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var subWidget: TextView
    private var streamWidth = 1080
    private var streamHeight = 1920

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainContainer = RelativeLayout(this)
        
        // Interactive Subscriber Count Scene Editor Widget
        subWidget = TextView(this).apply {
            text = "🔴 SUBS: 1,420"
            textSize = 24f
            setTextColor(Color.WHITE)
            setBackgroundColor(Color.parseColor("#CCFF0000"))
            setPadding(32, 16, 32, 16)
            gravity = Gravity.CENTER
        }

        val widgetParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT).apply {
            addRule(RelativeLayout.CENTER_IN_PARENT)
        }
        mainContainer.addView(subWidget, widgetParams)

        // Make the widget completely draggable anywhere on screen
        setupDraggableWidget(subWidget)

        // Lower Control Strip Layout Bar
        val controlPanel = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            setBackgroundColor(Color.parseColor("#AA000000"))
            gravity = Gravity.CENTER
        }
        
        val qualitySpinner = Spinner(this)
        val qualities = arrayOf("1080p (Shorts Ultra)", "720p (Shorts Mid)")
        qualitySpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, qualities)
        controlPanel.addView(qualitySpinner)

        val rtmpButton = Button(this).apply {
            text = "START SHORTS STREAM"
            setOnClickListener {
                Toast.makeText(this@MainActivity, "Broadcasting 9:16 layout straight to rtmp://://youtube.com", Toast.LENGTH_LONG).show()
            }
        }
        controlPanel.addView(rtmpButton)

        val layoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT).apply {
            addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
        }
        mainContainer.addView(controlPanel, layoutParams)
        setContentView(mainContainer)
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupDraggableWidget(view: View) {
        var dX = 0f
        var dY = 0f
        view.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> { dX = v.x - event.rawX; dY = v.y - event.rawY }
                MotionEvent.ACTION_MOVE -> { v.animate().x(event.rawX + dX).y(event.rawY + dY).setDuration(0).start() }
            }
            true
        }
    }
}

