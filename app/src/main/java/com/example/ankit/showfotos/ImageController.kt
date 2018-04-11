package com.example.ankit.showfotos

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/**
 * Created by ankit on 11/04/18.
 */
internal class ImageController(private var context: Context, private var imgMain: ImageView) {
  
  fun setImgMain(path: Uri) {
    val options = RequestOptions()
        .centerCrop()
    Glide
        .with(context)
        .load(path)
        .apply(options)
        .into(imgMain)
  }
}