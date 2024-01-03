package com.example.MadMemoirMix

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager

class ImageDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_detail)

        val backButton = findViewById<ImageButton>(R.id.icon_back)
        backButton.setOnClickListener {
            // go back to gallery fragment
            finish()
        }

        val imageAdapter = ImageAdapter(this)
        val viewPager: ViewPager = findViewById(R.id.viewPager)
        val imagePagerAdapter = ImagePagerAdapter(this, imageAdapter)
        viewPager.adapter = imagePagerAdapter

        // Get the initial position from the intent
        val initialPosition = intent.getIntExtra(EXTRA_POSITION, 0)
        viewPager.currentItem = initialPosition
    }

    private fun navigateToPage(cls: Class<*>) {
        val intent = Intent(this, cls)
        startActivity(intent)
        finish()
    }

    companion object {
        const val EXTRA_POSITION = "extra_position"
    }
}