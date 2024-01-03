package com.example.MadMemoirMix

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide

class ImageAdapter(private val context: Context) : BaseAdapter() {

    private val dummyImages = intArrayOf(
        R.drawable.happy_new_year_1,
        R.drawable.happy_new_year_2,
        R.drawable.happy_new_year_3,
        R.drawable.background,
        R.drawable.background_2,
        R.drawable.background_3,
        R.drawable.background_5,
        R.drawable.background_6,
        R.drawable.background_7,
        R.drawable.background_8,
        R.drawable.cloud_1,
        R.drawable.cloud_2,
        R.drawable.background_9,
        R.drawable.dummy_1,
        R.drawable.dummy_2,
        R.drawable.dummy_3,
        R.drawable.dummy_4,
        R.drawable.dummy_5,
        R.drawable.dummy_6,
        R.drawable.dummy_7,
        R.drawable.dummy_8,
        R.drawable.gallery_image_22,
        R.drawable.gallery_image_23,
        R.drawable.gallery_image_24,
        R.drawable.gallery_image_25,
        R.drawable.gallery_image_26,
        R.drawable.gallery_image_27,
        R.drawable.gallery_image_28,
        R.drawable.gallery_image_29,
        R.drawable.gallery_image_30,
        R.drawable.gallery_image_31,
        R.drawable.gallery_image_32,
        R.drawable.gallery_image_33,
        R.drawable.gallery_image_34,
        R.drawable.gallery_image_35,
        R.drawable.gallery_image_36,
        R.drawable.gallery_image_37,
        R.drawable.gallery_image_38,
    )

    private val targetImageSize: Int = calculateTargetImageSize(context)
    private var scaleType: ImageView.ScaleType = ImageView.ScaleType.CENTER_CROP


    private fun calculateTargetImageSize(context: Context): Int {
        // Adjust this value based on the desired size of your images
        val density = context.resources.displayMetrics.density
        return (120 * density).toInt() // 100dp as an example, adjust as needed
    }

    override fun getCount(): Int {
        return dummyImages.size
    }

    override fun getItem(position: Int): Any {
        return dummyImages[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val frameLayout: FrameLayout
        val imageView: ImageView

        if (convertView == null) {
            frameLayout = FrameLayout(context)
            frameLayout.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            imageView = ImageView(context)
            imageView.layoutParams = ViewGroup.LayoutParams(targetImageSize, targetImageSize)
            imageView.setPadding(8, 8, 8, 8)
            imageView.scaleType = scaleType

            // Add foreground to the FrameLayout to enable ripple effect
            frameLayout.foreground = ContextCompat.getDrawable(context, R.drawable.ripple_effect)

            frameLayout.addView(imageView)
        } else {
            frameLayout = convertView as FrameLayout
            imageView = frameLayout.getChildAt(0) as ImageView
        }

        // Load and set the image for the current position using Glide
        val imageResId = dummyImages[position]
        Glide.with(context)
            .load(imageResId)
            .into(imageView)

        // Handle item click to show larger view
        frameLayout.setOnClickListener {
            // Pass both imageResId and position to ImageDetailActivity
            val intent = Intent(context, ImageDetailActivity::class.java)
            intent.putExtra(ImageDetailActivity.EXTRA_POSITION, position)
            context.startActivity(intent)
        }

        return frameLayout
    }


    fun setScaleType(newScaleType: ImageView.ScaleType) {
        scaleType = newScaleType
        notifyDataSetChanged()
    }
}