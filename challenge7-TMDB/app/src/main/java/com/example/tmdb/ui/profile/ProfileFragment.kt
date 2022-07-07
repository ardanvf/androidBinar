package com.example.tmdb.ui.profile

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.app.ActivityCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.tmdb.BuildConfig
import com.example.tmdb.R
import com.example.tmdb.data.dao.UserViewModel
import com.example.tmdb.data.dao.UserViewModelFactory
import com.example.tmdb.data.preferences.Constant
import com.example.tmdb.data.preferences.Helper
import com.example.tmdb.databinding.FragmentProfileBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.ByteArrayOutputStream

class ProfileFragment : Fragment() {
    private lateinit var dataStore: DataStore<Preferences>
    private lateinit var binding: FragmentProfileBinding
    private lateinit var sharedPref: Helper
    private val args: ProfileFragmentArgs by navArgs()
    private val viewModel: UserViewModel by viewModel()
    val REQUEST_CODE = 200
    private val sharedPrefFile = "kotlinsharedpreference"


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
        dataStore = requireContext().createDataStore(name = "settings")
        sharedPref = Helper(requireContext())

        setImage()
        setTextViews()
        doubleBackToExit()
        binding.apply {
            btnToUpdate.setOnClickListener {
                toUpdateWithData()
            }
            btnLogout.setOnClickListener { loggingOut() }
            binding.profilePics.setOnClickListener {
                checkingPermissions()
            }
        }
    }

    private fun doubleBackToExit() {
        requireActivity().onBackPressedDispatcher.addCallback(requireActivity()) {
            findNavController().navigate(R.id.action_profileFragment_to_homeFragment)
        }
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
                    binding.tvName.text = data2.name
                    binding.fullname.text = data2.fname
                    binding.borndate.text = data2.date
                    binding.adress.text = data2.adress
                }
            }
        }

    }

    private fun loggingOut() {
        AlertDialog.Builder(requireContext())
            .setTitle("Logout?")
            .setMessage("Apakah anda yakin?")
            .setPositiveButton("Ya") { _, _ ->
                lifecycleScope.launch {
                    save("login", "False")
                }
                findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
            }
            .setNegativeButton("Batal") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun checkingPermissions() {

        if (isGranted(
                requireActivity(), Manifest.permission.CAMERA,
                arrayOf(
                    Manifest.permission.CAMERA,
                ),
                1,
            )
        ) {
            openCamera()
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
                val uri = Uri.fromParts("package", "com.example.tmdb", null)
                intent.data = uri
                startActivity(intent)

            }
            .setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }
            .show()
    }

    private suspend fun save(key: String, value: String) {
        val dataStoreKey = preferencesKey<String>(key)
        dataStore.edit { settings ->
            settings[dataStoreKey] = value
        }
    }

    private fun setImage() {
            val sharedPreferences: SharedPreferences = requireContext().getSharedPreferences(sharedPrefFile,
                Context.MODE_PRIVATE)
            val q = sharedPreferences.getString("name_key","defaultname")
            val imageBytes = Base64.decode(q, 0)
            val image = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
            activity?.runOnUiThread {
                Glide.with(requireContext())
                    .asBitmap()
                    .load(image)
                    .circleCrop()
                    .into(binding.profilePics)
            }
        }



    private fun openCamera() {
        var photo = Intent("android.media.action.IMAGE_CAPTURE");
        startActivityForResult(photo, REQUEST_CODE);
    }

    fun BitMapToString(bitmap: Bitmap): String {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 50, baos)
        Bitmap.createScaledBitmap(bitmap, 88, 88, false)
        val b = baos.toByteArray()
        return Base64.encodeToString(b, Base64.DEFAULT)

    }

    private fun handleCameraImage(intent: Intent?) {
        val uri = intent?.extras?.get("data") as Bitmap
        val x = BitMapToString(uri)
        val name: String = x
        val sharedPreferences: SharedPreferences = requireContext().getSharedPreferences(
            sharedPrefFile,
            Context.MODE_PRIVATE
        )
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("name_key", name)
        editor.apply()
        editor.commit()

        activity?.runOnUiThread {
            Glide.with(requireContext())
                .asBitmap()
                .load(uri)
                .circleCrop()
                .into(binding.profilePics)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
            handleCameraImage(data)
            val x = data?.data.toString()


            }
        }
    }









