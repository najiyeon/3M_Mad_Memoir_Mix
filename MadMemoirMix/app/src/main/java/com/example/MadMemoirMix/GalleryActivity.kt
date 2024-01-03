package com.example.MadMemoirMix

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity

class GalleryActivity : AppCompatActivity() {
    private var folderFragment = FolderFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        val backButton = findViewById<ImageButton>(R.id.icon_back)
        backButton.setOnClickListener {
            // if fragment stack is empty, finish activity
            if (supportFragmentManager.backStackEntryCount == 1 || supportFragmentManager.backStackEntryCount == 0) {
                navigateToPage(MainActivity::class.java)
            }
            // else pop fragment stack
            else {
                supportFragmentManager.popBackStack()
            }
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.imageGalleryLayout, folderFragment)
            .addToBackStack(null)
            .commit()

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