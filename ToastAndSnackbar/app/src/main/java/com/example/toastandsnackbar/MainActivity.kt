package com.example.toastandsnackbar

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.toastandsnackbar.databinding.ActivityMainBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener{
            if (binding.editText.text.isNullOrEmpty()){
                Toast.makeText(this, "Masukkan Nama Anda", Toast.LENGTH_LONG).show()
            } else {
                val dialog = BottomSheetDialog(this)
                dialog.setContentView(this.layoutInflater.inflate(R.layout.activity_snackbars,null))
                dialog.show()
            }
        }

        binding.button2.setOnClickListener{
            MaterialAlertDialogBuilder(this)
                .setTitle("Who Are You?")
                .setMessage("Human Or Robot")
                .setCancelable(true)
                .show()
        }

        binding.button3.setOnClickListener{
            MaterialAlertDialogBuilder(this)
                .setTitle("Who Am I?")
                .setNeutralButton("Nanti"){ savedInstanceState, which ->
                    showSnackBar("Okeey Boss")
                }.setNegativeButton("Robot"){ savedInstanceState, which ->
                    showSnackBar("Go Away!!!")
                }.setPositiveButton("Ardan"){ savedInstanceState, which ->
                    showSnackBar("Hello Boss")
                }
                .show()
        }

        binding.button4.setOnClickListener{
            val dialog = AlertDialog.Builder(this)
            val dialogView = layoutInflater.inflate(R.layout.activity_snackbars, null)
            dialog.setView(dialogView)
            dialog.setCancelable(true)
            dialog.show()
        }

        binding.button5.setOnClickListener{
            var dialog = fragment_dino()
            dialog.show(supportFragmentManager, null)
        }
    }
    private fun showSnackBar(msg: String){
        Snackbar.make(binding.root, msg, Snackbar.LENGTH_SHORT).show()
    }
}