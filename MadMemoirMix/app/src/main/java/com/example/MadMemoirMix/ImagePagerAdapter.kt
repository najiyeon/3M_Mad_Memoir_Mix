package com.example.MadMemoirMix

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.viewpager.widget.PagerAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog

class ImagePagerAdapter(private val context: Context, private val imageAdapter: ImageAdapter) : PagerAdapter() {

    override fun getCount(): Int {
        return imageAdapter.getCount()
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageView = ImageView(context)
        val imageResId = imageAdapter.getItem(position) as Int
        imageView.setImageResource(imageResId)
        imageView.scaleType = ImageView.ScaleType.FIT_CENTER

        imageView.setOnClickListener {
            showBottomSheetDialog(context, position)
        }

        container.addView(imageView)
        return imageView
    }

    private fun showBottomSheetDialog(context: Context, position: Int) {
        val view = LayoutInflater.from(context).inflate(R.layout.bottom_sheet_layout, null)
        val bottomSheetDialog = BottomSheetDialog(context)
        bottomSheetDialog.setContentView(view)

        val imageResId = imageAdapter.getItem(position) as Int
        val imageName = context.resources.getResourceEntryName(imageResId)
        val imageSize = getImageSize(context, imageResId)
        val imageSizeText = "${imageSize.first} x ${imageSize.second}"
        val imageFileSize = getImageFileSize(context, imageResId)

        view.findViewById<TextView>(R.id.imageNameTextView).text = "Image Name: $imageName"
        view.findViewById<TextView>(R.id.imageSizeTextView).text = "Image Size: $imageSizeText"
        view.findViewById<TextView>(R.id.imageFileSizeTextView).text = "File Size: $imageFileSize"

        // Make sure the bottom sheet is displayed above the ViewPager
        ViewCompat.setNestedScrollingEnabled(view.findViewById(R.id.bottomSheetLayout), true)

        bottomSheetDialog.show()
    }

    private fun getImageSize(context: Context, resourceId: Int): Pair<Int, Int> {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(context.resources, resourceId, options)
        return Pair(options.outWidth, options.outHeight)
    }

    private fun getImageFileSize(context: Context, resourceId: Int): String {
        val uri = Uri.parse("android.resource://${context.packageName}/$resourceId")
        val inputStream = context.contentResolver.openInputStream(uri)
        val fileSize = inputStream?.available() ?: 0
        inputStream?.close()
        return formatFileSize(fileSize)
    }

    private fun formatFileSize(size: Int): String {
        val units = arrayOf("B", "KB", "MB", "GB", "TB")
        var fileSize = size.toDouble()
        var index = 0
        while (fileSize > 1024) {
            fileSize /= 1024
            index++
        }
        return String.format("%.2f %s", fileSize, units[index])
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}