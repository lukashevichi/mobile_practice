package by.maxluxs.pr5

import android.Manifest
import android.content.ContentUris
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.util.Log
import android.widget.GridLayout
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import by.maxluxs.pr5.adapter.ImageAdapterCallback
import by.maxluxs.pr5.adapter.ImagesAdapter
import by.maxluxs.pr5.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import androidx.core.app.ActivityCompat

import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import androidx.activity.viewModels

import androidx.core.content.ContextCompat
import androidx.core.graphics.BitmapCompat
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ImageAdapterCallback {

    private lateinit var binding: ActivityMainBinding

    private val adapter: ImagesAdapter? get() = (binding.imagesRecycler.adapter as? ImagesAdapter)

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        renderRecycler()
        viewModel.imageList.observe(this) {
            Log.e("!!!", "observe")
            Log.e("!!!", it.toString())
            adapter?.submitList(it)
        }
    }

    private fun checkPerms() {
        if (ContextCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this@MainActivity,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) && ActivityCompat.shouldShowRequestPermissionRationale(
                    this@MainActivity,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
            ) {
            } else {
                ActivityCompat.requestPermissions(
                    this@MainActivity,
                    arrayOf(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    ),
                    100
                )
            }
        } else {
            Log.e("Else", "Else")
        }
    }

    private fun renderRecycler() = binding.imagesRecycler.apply {
        layoutManager = GridLayoutManager(this@MainActivity, 2)
        adapter = ImagesAdapter()
        setHasFixedSize(true)
    }

    override fun openImage(bitmap: Bitmap) {
        Toast.makeText(this, "Open image", Toast.LENGTH_SHORT).show()
    }

}