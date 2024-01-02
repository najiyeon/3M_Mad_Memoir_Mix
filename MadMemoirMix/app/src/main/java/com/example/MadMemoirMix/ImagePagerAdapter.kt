package com.example.MadMemoirMix

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter

class ImagePagerAdapter(private val context: Context, private val imageAdapter: ImageAdapter) : PagerAdapter() {

    override fun getCount(): Int {
        return imageAdapter.getCount()
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageView = ImageView(context)
        val imageResId = imageAdapter.getItem(position) as Int
        imageView.setImageResource(imageResId)
        imageView.scaleType = ImageView.ScaleType.FIT_CENTER
        container.addView(imageView)
        return imageView
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}