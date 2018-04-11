package com.example.ankit.showfotos.extensions

import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.request.RequestOptions

/**
 * Created by ankit on 11/04/18.
 */

@Suppress("NOTHING_TO_INLINE")
inline fun <T> RequestBuilder<T>.centerCrop(): RequestBuilder<T> {
  val options = RequestOptions()
      .centerCrop()
  return apply(options)
}