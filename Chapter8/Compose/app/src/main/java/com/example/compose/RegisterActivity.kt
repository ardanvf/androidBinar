package com.example.compose

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.ui.theme.ComposeTheme

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column {
                Column(modifier = Modifier.fillMaxSize(),
//                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Greeting("Daftar Akun")
                    TextFieldUsername()
                    TextFieldEmail()
                    TextFieldPassword("Masukkan Password")
                    TextFieldPassword("Konfirmasi Password")
                    RegisterButton()
                }
            }
        }
    }
}

@Composable
fun TextFieldEmail(){
    var email by remember{
        mutableStateOf("")
    }
    OutlinedTextField(
        value = email,
        leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "") },
        onValueChange = {email = it},
        label = { Text("Masukkan Email")}
    )
}

@Composable
fun RegisterButton(){
    val mContext = LocalContext.current

    Button(onClick = {
        Toast.makeText(mContext, "Berhasil Mendaftar", Toast.LENGTH_SHORT).show()
        mContext.startActivity(Intent(mContext, MainActivity::class.java))
    },
        Modifier
            .padding(top = 10.dp)
            .width(280.dp)) {
        Text(text = "Daftar", fontWeight = FontWeight.Bold, fontSize = 18.sp)
    }
}