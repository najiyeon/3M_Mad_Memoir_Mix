package com.example.MadMemoirMix

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager

class ImageDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_detail)

        val imageAdapter = ImageAdapter(this)
        val viewPager: ViewPager = findViewById(R.id.viewPager)
        val imagePagerAdapter = ImagePagerAdapter(this, imageAdapter)
        viewPager.adapter = imagePagerAdapter

        // Get the initial position from the intent
        val initialPosition = intent.getIntExtra(EXTRA_POSITION, 0)
        viewPager.currentItem = initialPosition
    }

    companion object {
        const val EXTRA_POSITION = "extra_position"
    }
}