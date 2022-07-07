package com.example.compose

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity

class MainActivity : ComponentActivity() {

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            Column {
                Column(modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,) {
                    gambar()
                    Greeting(name = "Movie Login")
                    TextFieldUsername()
                    TextFieldPassword("Masukkan Password")
                    LoginButton()
                    TextRegister("Belum punya akun?", "Daftar disini")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "$name", textAlign = TextAlign.Center, modifier = Modifier.width(300.dp),
        fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold, color = Color(0xFF3C1188), fontSize = 18.sp
    )
}

@Composable
fun TextRegister(register: String, clickHere: String){
    val mContext = LocalContext.current

    ClickableText(text = AnnotatedString("$register"), onClick = {
        mContext.startActivity(Intent(mContext, RegisterActivity::class.java))
    } )
    Text(text = "$clickHere", color = Color(0xFF5E2BB8))
}

@Composable
fun TextFieldUsername(){
    var text by remember{ mutableStateOf("")}

    OutlinedTextField(
        value = text,
        leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = "") },
        onValueChange = {text = it},
        label = { Text("Masukkan Username")}
    )
}

@Composable
fun TextFieldPassword(psw: String){
    var password by rememberSaveable {
        mutableStateOf("")
    }
    OutlinedTextField(value = password, onValueChange = {password = it},
    label = { Text(psw)},
    visualTransformation = PasswordVisualTransformation(),
        leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription ="" )},
    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )
}

@Composable
fun LoginButton(){
    val mContext = LocalContext.current
    Button(onClick = {
//        Toast.makeText(mContext, "Tombol Login sudah ditekan", Toast.LENGTH_SHORT).show()
        mContext.startActivity(Intent(mContext, MenuActivity::class.java))
    },
        Modifier
            .padding(top = 10.dp)
            .width(280.dp)) {
     Text(text = "Masuk", fontWeight = FontWeight.Bold, fontSize = 18.sp)
    }
}

@Composable
fun gambar() {
    Card(
        modifier = Modifier.size(88.dp),
        shape = CircleShape,
        elevation = 2.dp
    ) {
        Image(
            painterResource(R.drawable.logo),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}