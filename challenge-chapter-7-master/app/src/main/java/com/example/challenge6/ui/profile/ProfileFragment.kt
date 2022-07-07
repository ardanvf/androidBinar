package com.example.challenge6.ui.profile

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.datastore.core.DataStore
import androidx.datastore.createDataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.challenge6.R
import com.example.challenge6.data.UserApplication
import com.example.challenge6.data.UserViewModel
import com.example.challenge6.data.UserViewModelFactory
import com.example.challenge6.data.preferences.Constant
import com.example.challenge6.data.preferences.Helper
import com.example.challenge6.databinding.FragmentProfileBinding
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment() {

    private val viewModel: UserViewModel by viewModel()
    private lateinit var binding: FragmentProfileBinding
    private lateinit var sharedPref: Helper
    private lateinit var dataStore: DataStore<Preferences>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val profileBinding = FragmentProfileBinding.inflate(inflater, container, false)
        binding = profileBinding
        return profileBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTextViews()
        sharedPref = Helper(requireContext())

        dataStore = requireContext().createDataStore("Settings")

        binding.apply {

            lifecycleScope.launch (Dispatchers.IO){
                val value = read("Profile")
                activity?.runOnUiThread {
                    Glide.with(requireContext())
                        .asBitmap()
                        .load(value)
                        .circleCrop()
                        .into(profilePicture)
                }
            }

            btnToUpdate.setOnClickListener { toUpdateWithData() }
            btnLogout.setOnClickListener { loggingOut() }
            editPicture.setOnClickListener { checkingPermissions() }
        }
    }

    //save datastore
    private suspend fun save(key: String, value: String) {
        val dataStoreKey = preferencesKey<String>(key)
        dataStore.edit { settings ->
            settings[dataStoreKey] = value
        }
    }

    //read datastore
    private suspend fun read(key: String): String? {
        val dataStoreKey = preferencesKey<String>(key)
        val preferences = dataStore.data.first()
        return preferences[dataStoreKey]
    }

    private fun toUpdateWithData() {
        lifecycleScope.launch(Dispatchers.IO) {
            val data2 = viewModel.getUserProfile(sharedPref.getEmail(Constant.EMAIL_USER))
            activity?.runOnUiThread {
                val actionToProfileUpdate =
                    ProfileFragmentDirections.actionProfileFragmentToUpdateProfileFragment(data2)
                findNavController().navigate(actionToProfileUpdate)
            }
        }
    }

    private fun setTextViews() {
        lifecycleScope.launch(Dispatchers.IO) {
            val data2 = viewModel.getUserProfile(sharedPref.getEmail(Constant.EMAIL_USER))
            activity?.runOnUiThread {
                binding.apply {
                    tvName.text = data2.name
                    tvEmail.text = data2.email
                }
            }
        }
    }

    private fun loggingOut() {
        AlertDialog.Builder(requireContext())
            .setTitle("Logout?")
            .setMessage("Yakin ingin logout?")
            .setPositiveButton("Ya") { _, _ ->
                sharedPref.clear()
                findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
            }
            .setNegativeButton("Tidak") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    //ProfilePicture
    private fun checkingPermissions() {
        val REQUEST_CODE_PERMISSION = 100
        // isGranted berfungsi untuk meminta user untuk mengijinkan permission kita
        // di sini kita meminta permission camera, read/write storage
        if (isGranted(
                requireActivity(), Manifest.permission.CAMERA,
                arrayOf(
                    Manifest.permission.CAMERA,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ),
                REQUEST_CODE_PERMISSION,
            )
        ) {
            chooseImageDialog() // jika permission diberikan akan membuka sebuah dialog
        }
    }

    private fun isGranted(
        activity: Activity,
        permission: String,
        permissions: Array<String>,
        request: Int,
    ): Boolean {
        val permissionCheck = ContextCompat.checkSelfPermission(requireContext(), permission)
        return if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                showPermissionDeniedDialog()
            } else {
                ActivityCompat.requestPermissions(activity, permissions, request)
            }
            false
        } else {
            true
        }
    }

    private fun showPermissionDeniedDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Permission Denied")
            .setMessage("Permission is denied, Please allow permissions from App Settings.")
            .setPositiveButton(
                "App Settings"
            ) { _, _ ->
                val intent = Intent()
                intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                val uri = Uri.fromParts("package", activity?.getPackageName() , null)
                intent.data = uri
                startActivity(intent)
            }
            .setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }
            .show()
    }

    private fun chooseImageDialog(){
        AlertDialog.Builder(requireContext())
            .setMessage("Pilih Gambar")
            .setPositiveButton("Gallery") { _, _ -> openGallery() }
            .setNegativeButton("Camera") { _, _ -> openCamera() }
            .show()
    }

    private val galleryResult =
        registerForActivityResult(ActivityResultContracts.GetContent()) { result ->
            binding.profilePicture.setImageURI(result)
            lifecycleScope.launch (Dispatchers.IO) {
                save(
                    "Profile",
                    result.toString()
                )
            }
        }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        galleryResult.launch("image/*")
    }

    private val cameraResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                handleCameraImage(result.data)
            }
            lifecycleScope.launch (Dispatchers.IO){
                save(
                    "Profile",
                    result.toString()
                )
            }
        }

    private fun handleCameraImage(intent: Intent?) {
        val bitmap = intent?.extras?.get("data") as Bitmap
        binding.profilePicture.setImageBitmap(bitmap)
    }

    private fun openCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraResult.launch(cameraIntent)
    }
}