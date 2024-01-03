package com.example.MadMemoirMix

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.ToggleButton
import com.bumptech.glide.Glide

class ImageAdapter(private val context: Context) : BaseAdapter() {

    private val dummyImages = intArrayOf(
        R.drawable.gallery_image_01,
        R.drawable.gallery_image_02,
        R.drawable.gallery_image_03,
        R.drawable.gallery_image_04,
        R.drawable.gallery_image_05,
        R.drawable.gallery_image_06,
        R.drawable.gallery_image_07,
        R.drawable.gallery_image_08,
        R.drawable.gallery_image_09,
        R.drawable.gallery_image_10,
        R.drawable.gallery_image_11,
        R.drawable.gallery_image_12,
        R.drawable.gallery_image_13,
        R.drawable.gallery_image_14,
        R.drawable.gallery_image_15,
        R.drawable.gallery_image_16,
        R.drawable.gallery_image_17,
        R.drawable.gallery_image_18,
        R.drawable.gallery_image_19,
        R.drawable.gallery_image_20,
        R.drawable.gallery_image_21,
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
        val imageView: ImageView
        if (convertView == null) {
            imageView = ImageView(context)
            imageView.layoutParams = ViewGroup.LayoutParams(targetImageSize, targetImageSize)
            imageView.setPadding(8, 8, 8, 8)
        } else {
            imageView = convertView as ImageView
        }

        imageView.scaleType = scaleType

        // Load and set the image for the current position using Glide
        val imageResId = dummyImages[position]
        Glide.with(context)
            .load(imageResId)
            .into(imageView)

        // Handle item click to show larger view
        imageView.setOnClickListener {
            // Pass both imageResId and position to ImageDetailActivity
            val intent = Intent(context, ImageDetailActivity::class.java)
            intent.putExtra(ImageDetailActivity.EXTRA_POSITION, position)
            context.startActivity(intent)
        }

        return imageView
    }

    fun setScaleType(newScaleType: ImageView.ScaleType) {
        scaleType = newScaleType
        notifyDataSetChanged()
    }
}