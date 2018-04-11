package com.example.ankit.showfotos.feature

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.ankit.showfotos.extensions.centerCrop

/**
 * Created by ankit on 11/04/18.
 */
internal class ImageController(private var context: Context, private var imgMain: ImageView) {
  
  fun setImgMain(path: Uri) {
    Glide
        .with(context)
        .load(path)
        .centerCrop()
        .into(imgMain)
  }
}