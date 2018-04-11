package com.example.ankit.showfotos

import android.content.Context
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.util.ArrayList

/**
 * Created by ankit on 11/04/18.
 */
internal class ImageAdapter(private var context: Context, private var imageController: ImageController, private var imagePaths: ArrayList<Uri>) : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {
  
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context)
        .inflate(R.layout.item, parent, false)
    return ViewHolder(view)
  }
  
  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val imagePath = imagePaths[position]
    val options = RequestOptions()
        .centerCrop()
    
    Glide.with(context)
        .load(imagePath)
        .apply(options)
        .into(holder.imageView)
    holder.imageView.setOnClickListener { imageController.setImgMain(imagePath) }
  }
  
  fun changePath(imagePaths: ArrayList<Uri>) {
    this.imagePaths = imagePaths
    imageController.setImgMain(imagePaths[0])
    notifyDataSetChanged()
  }
  
  override fun getItemCount(): Int {
    return imagePaths.size
  }
  
  inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    internal var imageView: ImageView = itemView.findViewById<View>(R.id.img_item) as ImageView
  }
}