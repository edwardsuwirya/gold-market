package com.enigmacamp.goldmarket.fragments

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.enigmacamp.goldmarket.Customer
import com.enigmacamp.goldmarket.MainActivity
import com.enigmacamp.goldmarket.R
import com.google.android.material.snackbar.Snackbar
import java.io.File
import java.io.FileOutputStream


/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters

    lateinit var customerName: TextView
    lateinit var customerEmail: TextView
    lateinit var statusTextView: TextView
    lateinit var uploadIdButton: Button
    lateinit var profileImage: ImageView
    lateinit var idImage: ImageView

    val TAG = "ProfileFragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun haveStoragePermission() = ContextCompat.checkSelfPermission(
        this.requireContext(),
        Manifest.permission.READ_EXTERNAL_STORAGE
    ) == PackageManager.PERMISSION_GRANTED

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (!haveStoragePermission()) {
            val permissions = arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            ActivityCompat.requestPermissions(
                this.requireActivity(), permissions, EXTERNAL_STORAGE_PERMISSION
            )
        } else {
            if (resultCode == RESULT_OK && requestCode == ID_PHOTO && data != null) {
                context?.run {
                    val selectedImage = data.data
                    Log.d(TAG, selectedImage.toString())
                    val image = getImageDir()
                    val fos = FileOutputStream(image)

                    val bitmap =
                        MediaStore.Images.Media.getBitmap(contentResolver, selectedImage)

                    fos?.use {
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)
                        val snackbar =
                            Snackbar.make(view!!, "Succesfully add id", Snackbar.LENGTH_LONG)
                        val snackbarView = snackbar.view
                        snackbarView.background = getDrawable(R.drawable.background_with_radius)
                        val textView =
                            snackbarView.findViewById(R.id.snackbar_text) as TextView
                        textView.setTextColor(getColor(R.color.colorPrimaryDark))
                        textView.textSize = 18f
                        snackbar.show()
                    }
                    loadImage()
                }
//            val stream = resolver?.openOutputStream(destUri!!)
//            bitmap.compress(Bitmap.CompressFormat.PNG, 85, stream);

//            val bitmap =
//                MediaStore.Images.Media.getBitmap(this.activity?.contentResolver, selectedImage)
//
//            val myDir = context!!.filesDir
//            val imagesDir = "images/id"
//            val imageDirectory = File(myDir, imagesDir)
//            if (!imageDirectory.exists()) {
//                imageDirectory.mkdirs();
//            }
//            Log.d(TAG, myDir.absolutePath)
//            val fileDst = File(imageDirectory.absolutePath, "idcard.png")
//            val fos = FileOutputStream(fileDst)
//            bitmap.compress(Bitmap.CompressFormat.PNG, 85, fos);
//            fos.flush()
//            fos.close()
            }
        }

    }

    private fun getImageDir(): File? {
        var imageFile: File? = null
        context?.run {
            val imagesDir = getExternalFilesDir(null)
            imageFile = File(imagesDir, "id.png")
            Log.d(TAG, imageFile?.absolutePath)
        }
        return imageFile
    }

    private fun loadImage() {
        val imageFile = getImageDir()
        idImage?.setImageURI(Uri.fromFile(imageFile));
        //Alternative way using BitmapFactory
        //val myBitmap = BitmapFactory.decodeFile(imageFile?.getAbsolutePath())
        //idImage?.setImageBitmap(myBitmap)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view: View = inflater.inflate(R.layout.fragment_profile, container, false)

        val bundle = arguments
        val customer = bundle?.getParcelable<Customer>(MainActivity.PROFILE_KEY)

        customerName = view.findViewById(R.id.customerName_textView)
        customerName.text = customer?.firstName ?: ""

        customerEmail = view.findViewById(R.id.customerEmail_textView)
        customerEmail.text = customer?.email ?: ""

        uploadIdButton = view.findViewById(R.id.upload_id_button)
        uploadIdButton.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_GET_CONTENT,
                MediaStore.Images.Media.INTERNAL_CONTENT_URI
            )
            intent.type = "image/*"
            startActivityForResult(intent, ID_PHOTO)
        }

        profileImage = view.findViewById(R.id.profile_imageView)
        profileImage.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_GET_CONTENT,
                MediaStore.Images.Media.INTERNAL_CONTENT_URI
            )
            intent.type = "image/*"
            startActivityForResult(intent, PROFILE_PHOTO)
        }

        statusTextView = view.findViewById(R.id.status_textView)
        statusTextView.text = "Belum terverifikasi"

        idImage = view?.findViewById<ImageView>(R.id.id_imageView)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadImage()
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        const val PROFILE_PHOTO = 1000
        const val ID_PHOTO = 1001
        const val EXTERNAL_STORAGE_PERMISSION = 2222

        @JvmStatic
        fun newInstance() = ProfileFragment()
    }
}