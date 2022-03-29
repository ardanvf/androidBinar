package com.example.toastandsnackbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.toastandsnackbar.databinding.ActivityMainBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener{
            if (binding.editText    .text.isNullOrEmpty()){
                Toast.makeText(this, "Masukkan Nama Anda", Toast.LENGTH_LONG).show()
            } else {
                val dialog = BottomSheetDialog(this)
                dialog.setContentView(this.layoutInflater.inflate(R.layout.activity_snackbars,null))
                dialog.show()
            }
        }
    }
}