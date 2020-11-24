package com.enigmacamp.goldmarket.ui.main.view.fragments

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.enigmacamp.goldmarket.R
import com.enigmacamp.goldmarket.data.model.Customer
import com.enigmacamp.goldmarket.databinding.FragmentProfileBinding
import com.enigmacamp.goldmarket.ui.base.AppBaseFragment
import com.enigmacamp.goldmarket.ui.main.view.activity.MainActivity
import com.enigmacamp.goldmarket.ui.main.viewmodel.ProfileFragmentViewModel
import com.google.android.material.snackbar.Snackbar


/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : AppBaseFragment() {
    // TODO: Rename and change types of parameters
    private lateinit var binding: FragmentProfileBinding
    lateinit var viewModel: ProfileFragmentViewModel

    val TAG = ProfileFragment::class.qualifiedName

    private fun initUi() {
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(ProfileFragmentViewModel::class.java)
    }

    private fun haveStoragePermission() = ContextCompat.checkSelfPermission(
        this.requireContext(),
        Manifest.permission.READ_EXTERNAL_STORAGE
    ) == PackageManager.PERMISSION_GRANTED

    private fun createSnackBar(message: String) {
        val snackbar =
            Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG)
        val snackbarView = snackbar.view
        snackbarView.background = requireContext().getDrawable(R.drawable.background_with_radius)
        val textView =
            snackbarView.findViewById(R.id.snackbar_text) as TextView
        textView.setTextColor(requireContext().getColor(R.color.colorPrimaryDark))
        textView.textSize = 18f
        snackbar.show()
    }

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

                    val result = viewModel.saveInternalStorage(selectedImage!!, this, ID_IMAGE_NAME)
                    if (result) {
                        createSnackBar("Succesfully add ID")
                        loadImage(ID_IMAGE_NAME)
                    } else {
                        createSnackBar("Error creating ID")
                    }
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

    private fun loadImage(imageName: String) {
        val imageFile = viewModel.getImageDir(requireContext(), imageName)
        binding.idImageView.setImageURI(null)
        binding.idImageView.setImageURI(Uri.fromFile(imageFile));
        //Alternative way using BitmapFactory
        //val myBitmap = BitmapFactory.decodeFile(imageFile?.getAbsolutePath())
        //idImage?.setImageBitmap(myBitmap)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initUi()
        initViewModel()
        val title = arguments?.getString(MainActivity.TITLE_KEY)
        requireActivity().title = title
        val customer = arguments?.getParcelable<Customer>(MainActivity.PROFILE_KEY)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.apply {
            binding.profileFragmentViewModel = viewModel
            viewModel.setProfileInfo(
                customer?.firstName ?: "",
                customer?.email ?: "",
                "Belum terverifikasi"
            )

            uploadIdButton.setOnClickListener {
                val intent = Intent(
                    Intent.ACTION_GET_CONTENT,
                    MediaStore.Images.Media.INTERNAL_CONTENT_URI
                )
                intent.type = "image/*"
                startActivityForResult(intent, ID_PHOTO)
            }

            profileImageView.setOnClickListener {
                val intent = Intent(
                    Intent.ACTION_GET_CONTENT,
                    MediaStore.Images.Media.INTERNAL_CONTENT_URI
                )
                intent.type = "image/*"
                startActivityForResult(intent, PROFILE_PHOTO)
            }
        }

        loadImage(ID_IMAGE_NAME)
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        const val PROFILE_PHOTO = 1000
        const val ID_PHOTO = 1001
        const val EXTERNAL_STORAGE_PERMISSION = 2222
        const val ID_IMAGE_NAME = "id.png"

        @JvmStatic
        fun newInstance() = ProfileFragment()
    }
}