package com.enigmacamp.goldmarket.ui.main.viewmodel

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.ViewModel
import com.enigmacamp.goldmarket.R
import com.google.android.material.snackbar.Snackbar
import java.io.File
import java.io.FileOutputStream

class ProfileFragmentViewModel : ViewModel() {

    fun saveInternalStorage(imageUri: Uri, context: Context, imageName: String): Boolean {
        val image = getImageDir(context, imageName)
        val fos = FileOutputStream(image)
        try {
            val bitmap =
                MediaStore.Images.Media.getBitmap(context.contentResolver, imageUri)
            fos?.use {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)
            }
            return true

        } catch (e: Exception) {
            return false
        } finally {
            fos.close()
        }
    }

    fun getImageDir(context: Context, imageName: String): File? {
        var imageFile: File? = null
        context?.run {
            val imagesDir = getExternalFilesDir(null)
            imageFile = File(imagesDir, imageName)
        }
        return imageFile
    }


}