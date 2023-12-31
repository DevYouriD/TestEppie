package com.example.testeppie.views

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.testeppie.R

class MainActivity : AppCompatActivity() {
    private lateinit var counterTextView: TextView
    private lateinit var increaseButton: Button
    private lateinit var decreaseButton: Button
    private lateinit var resetButton: Button

    private var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        counterTextView = findViewById(R.id.textView5)
        increaseButton = findViewById(R.id.increaseButton)
        decreaseButton = findViewById(R.id.decreaseButton)
        resetButton = findViewById(R.id.resetButton)

        increaseButton.setOnClickListener {
            counter++
            counterTextView.text = counter.toString()
        }

        decreaseButton.setOnClickListener {
            counter--
            counterTextView.text = counter.toString()
        }

        resetButton.setOnClickListener {
            counter = 0
            counterTextView.text = counter.toString()
        }
    }
}

//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            setContentView(R.layout.activity_main)
//        }
//    }
//}
