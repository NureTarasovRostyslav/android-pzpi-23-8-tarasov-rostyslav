package com.nure_ua_tarasov.labtask2

import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Color
import android.graphics.ColorSpace.Rgb
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.ViewGroup
import android.view.WindowMetrics
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val panel = findViewById<LinearLayout>(R.id.color_panel)

        val red = findViewById<SeekBar>(R.id.seekBarR)
        val green = findViewById<SeekBar>(R.id.seekBarG)
        val blue = findViewById<SeekBar>(R.id.seekBarB)

        var r = red.progress
        var g = green.progress
        var b = blue.progress

        for (i in listOf<SeekBar>(red, green, blue)) {
            i.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                    when (seekBar.id) {
                        R.id.seekBarR -> r = seekBar.progress
                        R.id.seekBarG -> g = seekBar.progress
                        R.id.seekBarB -> b = seekBar.progress
                    }
                    panel.setBackgroundColor(Color.rgb(r, g, b))
                }

                override fun onStartTrackingTouch(seekBar: SeekBar) {

                }

                override fun onStopTrackingTouch(seekBar: SeekBar) {

                }
            })
        }
    }

}