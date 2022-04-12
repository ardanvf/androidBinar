package com.example.test_json

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.test_json.databinding.ActivityMainBinding
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val jsonArray = JSONArray()
        val jsonObject = JSONObject()
        jsonObject.put("status", "OK")
        jsonObject.put("message", "Success")

        val jsonBook = JSONObject()
            .put("name", "Book")
            .put("price",100000)
            .put("code", "CD120103929")
            .put("discount", 70000)
            .put("qty", 1)
            .put("new", true)

        val jsonCandle = JSONObject()
            .put("name", "Candle")
            .put("Price", 150000)
            .put("code", "CD120103929")
            .put("discount", 70000)
            .put("qty", 2)
            .put("new", true)
        jsonArray.put(jsonBook)
        jsonArray.put(jsonCandle)

        val jsonData = JSONObject()
            .put("ammount", 180000)
            .put("qty", 3)
            .put("paid",200000)
            .put("change", 20000)
            .put("detail", jsonArray)

        val jsonReceipt = JSONObject()
            .put("store", "Uni.oq")
            .put("address", "Bandung")
            .put("add_note","All you need")

        //data
        jsonObject.put("data", jsonData)
        jsonObject.put("detail", jsonArray)
        //receipt
        jsonObject.put("receipt", jsonReceipt)

        val nameToko = jsonObject.getJSONObject("receipt").getJSONObject("store")
        val lokasiToko = jsonObject.getJSONObject("receipt").getString("address")
        val noteToko = jsonObject.getJSONObject("receipt").getString("add_note")

        val price = jsonObject.getJSONObject("data").getJSONArray("detail").getJSONObject(0).getInt("price")
 //       val price2 = jsonObject.getJSONObject("data").getJSONArray("detail").getJSONObject(1).getInt("price")
//        val barang1 = jsonObject.getJSONObject("data").getJSONArray("detail").getJSONObject(0).getString("name")
//        val barang2 = jsonObject.getJSONObject("data").getJSONArray("detail").getJSONObject(1).getString("name")
//        val qty1 = jsonObject.getJSONObject("data").getJSONArray("detail").getJSONObject(0).getInt("qty")
//        val qty2 = jsonObject.getJSONObject("data").getJSONArray("detail").getJSONObject(1).getInt("qty")


        binding.textView.text = nameToko.toString()
        binding.textView2.text = lokasiToko.toString()
        binding.textView3.text = noteToko.toString()

//        binding.textView4.text = barang1.toString()
//        binding.textView5.text = barang2.toString()
        binding.textView7.text = price.toString()
//        binding.textView8.text = price2.toString()

    }
}