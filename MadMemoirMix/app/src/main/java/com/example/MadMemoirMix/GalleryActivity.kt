package com.example.MadMemoirMix

import android.content.Intent
import android.os.Bundle
import android.widget.GridView
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class GalleryActivity : AppCompatActivity() {
    private lateinit var imageAdapter: ImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        val backButton = findViewById<ImageButton>(R.id.icon_back)
        backButton.setOnClickListener {
            navigateToPage(MainActivity::class.java)
        }

        // Initialize and set up your image gallery (GridView)
        val gridView: GridView = findViewById(R.id.gridView)
        imageAdapter = ImageAdapter(this)
        gridView.adapter = imageAdapter

        // Image ratio toggle
        val toggleButton: ToggleButton = findViewById(R.id.toggleButton)
        toggleButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                imageAdapter.setScaleType(ImageView.ScaleType.CENTER_INSIDE)
            } else {
                imageAdapter.setScaleType(ImageView.ScaleType.CENTER_CROP)
            }
        }

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