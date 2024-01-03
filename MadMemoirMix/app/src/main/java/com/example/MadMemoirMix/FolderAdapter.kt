package com.example.MadMemoirMix

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.Glide

class FolderAdapter(private val context: Context, fragmentManager : FragmentManager) : BaseAdapter() {
    private val targetImageSize: Int = calculateTargetImageSize(context)
    private var folderinFragment = ForderinFragment()
    private var mFragmentManager : FragmentManager

    init {
        mFragmentManager = fragmentManager
    }

    private val thumbnails = intArrayOf(
        R.drawable.gallery_image_01,
        R.drawable.gallery_image_08,
        R.drawable.gallery_image_15,
    )


    private fun calculateTargetImageSize(context: Context): Int {
        // Adjust this value based on the desired size of your images
        val density = context.resources.displayMetrics.density
        return (180 * density).toInt() // 100dp as an example, adjust as needed
    }

    override fun getCount(): Int {
        return thumbnails.size
    }

    override fun getItem(position: Int): Any {
        return thumbnails[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val imageView: ImageView
        if (convertView == null) {
            // If convertView is null, inflate the layout from folder_item.xml
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view = inflater.inflate(R.layout.forder_item, parent, false)

            // Assuming folder_item.xml contains an ImageView with id "imageView"
            imageView = view.findViewById(R.id.imageView)
//            imageView.layoutParams = ViewGroup.LayoutParams(targetImageSize, targetImageSize)
//            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
//            imageView.setPadding(8, 8, 8, 8)
        } else {
            imageView = convertView.findViewById(R.id.imageView) as ImageView
        }

        // Load and set the image for the current position using Glide
        val imageResId = thumbnails[position]
        Glide.with(context)
            .load(imageResId)
            .into(imageView)

        // Handle item click to show images in the folder
        if (position == 0) {
            imageView.setOnClickListener {
                mFragmentManager.beginTransaction()
                    .replace(R.id.imageGalleryLayout, folderinFragment)
                    .addToBackStack(null)
                    .commit()
            }
        }


        return imageView
    }
}