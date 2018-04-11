package com.example.ankit.showfotos.feature

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.example.ankit.showfotos.R
import com.sangcomz.fishbun.FishBun
import com.sangcomz.fishbun.adapter.image.impl.GlideAdapter
import com.sangcomz.fishbun.define.Define
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
  
  internal var path = ArrayList<Uri>()
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    recycler.adapter = ImageAdapter(this, ImageController(this, img), path)
    FishBun.with(this)
        .setImageAdapter(GlideAdapter())
        .setMaxCount(10)
        .setPickerSpanCount(5)
        .setActionBarColor(Color.parseColor("#795548"), Color.parseColor("#5D4037"), false)
        .setActionBarTitleColor(Color.parseColor("#ffffff"))
        .setSelectedImages(path)
        .setAlbumSpanCount(2, 3)
        .setButtonInAlbumActivity(false)
        .setReachLimitAutomaticClose(true)
        .setHomeAsUpIndicatorDrawable(ContextCompat.getDrawable(this, R.drawable.back))
        .setOkButtonDrawable(ContextCompat.getDrawable(this, R.drawable.done))
        .setAllViewTitle("All")
        .setActionBarTitle("FishBun Dark")
        .textOnNothingSelected("Please select three or more!")
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
      
      return true
    }
    return super.onOptionsItemSelected(item)
  }
  
  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    when (requestCode) {
      Define.ALBUM_REQUEST_CODE -> if (resultCode == Activity.RESULT_OK) {
        path = data?.getParcelableArrayListExtra<Uri>(Define.INTENT_PATH) as ArrayList<Uri>
        (recycler.adapter as ImageAdapter).changePath(path)
      }
    }
  }
}
