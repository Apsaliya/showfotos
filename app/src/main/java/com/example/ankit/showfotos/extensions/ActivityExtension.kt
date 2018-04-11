package com.example.ankit.showfotos.extensions

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import com.example.ankit.showfotos.extensions.util.Constants
import com.example.ankit.showfotos.extensions.util.Constants.PERMISSION_STORAGE

/**
 * Created by ankit on 11/04/18.
 */
@Suppress("NOTHING_TO_INLINE")
inline fun Activity.checkStoragePermission(): Boolean {
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
    val permissionCheckRead = ContextCompat.checkSelfPermission(this,
        Manifest.permission.READ_EXTERNAL_STORAGE)
    val permissionCheckWrite = ContextCompat.checkSelfPermission(this,
        Manifest.permission.WRITE_EXTERNAL_STORAGE)
    if (permissionCheckRead != PackageManager.PERMISSION_GRANTED || permissionCheckWrite != PackageManager.PERMISSION_GRANTED) {
      if (ActivityCompat.shouldShowRequestPermissionRationale(this,
              Manifest.permission.READ_EXTERNAL_STORAGE)) {
        ActivityCompat.requestPermissions(this,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE),
            PERMISSION_STORAGE)
      } else {
      
        ActivityCompat.requestPermissions(this,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE),
            PERMISSION_STORAGE)
      
      }
      return false
    } else
      return true
  }
  return true
}
