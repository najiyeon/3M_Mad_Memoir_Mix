package com.example.MadMemoirMix

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity

class GalleryActivity : AppCompatActivity() {
    private var folderFragment = FolderFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        val backButton = findViewById<ImageButton>(R.id.icon_back)
        backButton.setOnClickListener {
            navigateToPage(MainActivity::class.java)
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.imageGalleryLayout, folderFragment)
            .addToBackStack(null)
            .commit()

        // Image ratio toggle
//        val toggleButton: ToggleButton = findViewById(R.id.toggleButton)
//        toggleButton.setOnCheckedChangeListener { _, isChecked ->
//            if (isChecked) {
//                imageAdapter.setScaleType(ImageView.ScaleType.CENTER_INSIDE)
//            } else {
//                imageAdapter.setScaleType(ImageView.ScaleType.CENTER_CROP)
//            }
//        }

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