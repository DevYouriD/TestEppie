package com.example.testeppie

import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var counterTextView: TextView
    private lateinit var increaseButton: Button
    private lateinit var decreaseButton: Button
    private lateinit var resetButton: Button

    private var counter = 0

    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Drawer menu items
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // ?. is a null check (Elvise operator)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val navigationView = findViewById<NavigationView>(R.id.navView)
        navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menuItem1 -> Toast.makeText(
                    applicationContext,
                    "Clicked item 1",
                    Toast.LENGTH_SHORT
                ).show()

                R.id.menuItem2 -> Toast.makeText(
                    applicationContext,
                    "Clicked item 2",
                    Toast.LENGTH_SHORT
                ).show()
            }
            // True because we handled the click
            true
        }

        // Basic logic for the test app counter
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

    /**
     * Used to response correctly on click on the toggle button and the clicks
     * on the menu items in the navigation drawer.
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
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
