package com.example.tmdb.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.tmdb.R
import com.example.tmdb.data.dao.UserViewModel
import com.example.tmdb.databinding.FragmentUpdateProfileBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class UpdateProfileFragment : Fragment() {

    private lateinit var binding: FragmentUpdateProfileBinding
    private val args: UpdateProfileFragmentArgs by navArgs()
    private val viewModel: UserViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val updateProfileBinding = FragmentUpdateProfileBinding.inflate(inflater, container, false)
        binding = updateProfileBinding
        return updateProfileBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTextViews()
        doubleBackToExit()
        binding.apply {
            btnUpdate.setOnClickListener {
                toProfilePage()
            }
        }
    }

    private fun setTextViews() {
        binding.apply {
            etUpdateName.setText(args.data2?.name)
            namalengkap.setText(args.data2?.fname)
            tanggallahir.setText(args.data2?.date)
            alamat.setText(args.data2?.adress)}
            }

    private fun doubleBackToExit() {
        requireActivity().onBackPressedDispatcher.addCallback(requireActivity()) {
            findNavController().navigate(R.id.action_updateProfileFragment_to_profileFragment)
        }
    }

    private fun toProfilePage() {
        lifecycleScope.launch(Dispatchers.IO) {
            viewModel.userProfile(
                args.data2?.id!!,
                binding.etUpdateName.text.toString(),
                binding.namalengkap.text.toString(),
                binding.tanggallahir.text.toString(),
                binding.alamat.text.toString(),
                args.data2?.email!!,
                args.data2?.password!!,
                args.data2?.pitcure!!)
            activity?.runOnUiThread {
                Toast.makeText(requireContext(), "Changed successfully!", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_updateProfileFragment_to_profileFragment)
            }
        }
    }
}