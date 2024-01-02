package com.example.MadMemoirMix

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MinigameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_minigame)

        val backButton = findViewById<ImageButton>(R.id.icon_back)
        backButton.setOnClickListener {
            navigateToPage(MainActivity::class.java)
        }

        // default fragment
        val fragment = CoinFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()

        val switch = findViewById<android.widget.Switch>(R.id.switch1)

        switch.setOnCheckedChangeListener{CompoundButton, onSwitch ->
            if (onSwitch) {
                // goto dice game fragment
                val fragment = DiceFragment()
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragment_container, fragment)
                transaction.commit()
            } else {
                // goto coin game fragment
                val fragment = CoinFragment()
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragment_container, fragment)
                transaction.commit()
            }
        }

        // bottom navigation
        val bottomNavigationView = findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(R.id.navigationBar)
        bottomNavigationView.selectedItemId = R.id.menu_MiniGame

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_Contact -> {
                    navigateToPage(ContactActivity::class.java)
                    true
                }
                R.id.menu_Gallery -> {
                    navigateToPage(GalleryActivity::class.java)
                    true
                }
                R.id.menu_MiniGame -> {
                    navigateToPage(MinigameActivity::class.java)
                    true
                }
                else -> false
            }
        }
    }

    private fun navigateToPage(cls: Class<*>) {
        val intent = Intent(this, cls)
        startActivity(intent)
        finish()
    }
}