package com.example.MadMemoirMix

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView

class Folder2 : Fragment() {
    private lateinit var galleryActivity: GalleryActivity
    private lateinit var imageAdapter: ImageAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        galleryActivity = context as GalleryActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_folder1, container, false)
        val gridView: GridView = view.findViewById(R.id.imageGridView)
        imageAdapter = ImageAdapter(galleryActivity, 1)
        gridView.adapter = imageAdapter

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Folder2().apply {
                arguments = Bundle().apply {
                }
            }
    }
}