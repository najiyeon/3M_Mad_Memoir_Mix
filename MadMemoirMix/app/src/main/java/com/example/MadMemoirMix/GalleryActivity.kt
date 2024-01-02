package com.example.MadMemoirMix

import android.content.Intent
import android.os.Bundle
import android.widget.GridView
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class GalleryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        val backButton = findViewById<ImageButton>(R.id.icon_back)
        backButton.setOnClickListener {
            navigateToPage(MainActivity::class.java)
        }

        // Initialize and set up your image gallery (GridView)
        val gridView: GridView = findViewById(R.id.gridView)
        val imageAdapter = ImageAdapter(this)
        gridView.adapter = imageAdapter

        // bottom navigation
        val bottomNavigationView = findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(R.id.navigationBar)
        bottomNavigationView.selectedItemId = R.id.menu_Gallery

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