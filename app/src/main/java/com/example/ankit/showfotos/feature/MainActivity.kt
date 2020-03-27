package com.example.ankit.showfotos.feature

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.ankit.showfotos.R
import com.example.ankit.showfotos.extensions.*
import com.example.ankit.showfotos.extensions.util.Constants.PERMISSION_STORAGE
import com.sangcomz.fishbun.FishBun
import com.sangcomz.fishbun.adapter.image.impl.GlideAdapter
import com.sangcomz.fishbun.define.Define
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
  
  private var uris = ArrayList<Uri>() // this is a comment
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    if (checkStoragePermission()) {
      showGallery()
    }
    recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    recycler.adapter = ImageAdapter(this, ImageController(this, img), uris)
  }
  
  override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
    when (requestCode) {
      PERMISSION_STORAGE -> {
        if (grantResults.isNotEmpty()) {
          if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // permission was granted, yay!
            showGallery()
          } else {
            Toast.makeText(this, R.string.permission_deny_msg, Toast.LENGTH_SHORT).show()
            finish()
          }
        }
      }
    }
  }
  
  private fun showGallery() {
    uris.clear()
    FishBun.with(this)
        .setImageAdapter(GlideAdapter())
        .setSelectedImages(uris)
        .prepareActionBar(this)
        .setCounts()
        .setDefaults()
        .setDrawables(this)
        .setTexts(this)
        .startAlbum()
  }
  
  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    val inflater = menuInflater
    inflater.inflate(R.menu.menu_main, menu)
    return super.onCreateOptionsMenu(menu)
  }
  
  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    val id = item.itemId
    if (id == R.id.action_plus) {
      showGallery()
      return true
    }
    return super.onOptionsItemSelected(item)
  }
  
  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    when (requestCode) {
      Define.ALBUM_REQUEST_CODE -> if (resultCode == Activity.RESULT_OK) {
        uris = data?.getParcelableArrayListExtra<Uri>(Define.INTENT_PATH) as ArrayList<Uri>
        (recycler.adapter as ImageAdapter).dispatchUpdates(uris)
      }
    }
  }
}
