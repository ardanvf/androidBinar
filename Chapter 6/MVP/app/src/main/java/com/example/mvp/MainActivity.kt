package com.example.mvp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.mvp.Model.MainPresenterImp
import com.example.mvp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainView{

    private lateinit var binding: ActivityMainBinding
    private lateinit var presenterImp: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenterImp = MainPresenterImp(this)

        binding.btnAdd.setOnClickListener {
            presenterImp.add(binding.firstName.text.toString(), binding.lastName.text.toString())
        }

        binding.btnShow.setOnClickListener {
            presenterImp.loadData()
        }
    }

    override fun showMessage(message: String){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun showData(data: String){
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("Data")
        dialog.setMessage(data)
        dialog.setCancelable(true)
        dialog.show()
    }

    override fun clearField(){
        binding.firstName.text.clear()
        binding.lastName.text.clear()
    }
}