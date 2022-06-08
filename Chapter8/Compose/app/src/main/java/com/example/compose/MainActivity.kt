package com.example.compose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContent {
//            Column(
//                modifier = Modifier.fillMaxSize(),
//                verticalArrangement = Arrangement.Bottom,
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Greeting(name = "Ardan Venora")
//            }
//            SimpleColumnComponent()
//        }
        setContent{
            Column {
                Column(modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,) {
                    gambar()
                    Greeting(name = "Ardan")
                    TextFieldUsername()
                    TextFieldPassword()
                    LoginButton()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!", textAlign = TextAlign.Center, modifier = Modifier.width(300.dp),
        fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold, color = Color(0xFF0230BB), fontSize = 30.sp
    )
}
@Composable
fun SimpleColumnComponent(){
    Column(modifier = Modifier.padding(16.dp)){
        Text(text = "Hello! i am text 1", color = Color.Black)
        Text(text = "Hello!, i am text 2", color = Color.Red)
    }
}

@Composable
fun TextFieldUsername(){
    var text by remember{ mutableStateOf("")}

    OutlinedTextField(
        value = text,
        onValueChange = {text = it},
        label = { Text("Masukkan Username")}
    )
}

@Composable
fun TextFieldPassword(){
    var password by rememberSaveable {
        mutableStateOf("")
    }
    OutlinedTextField(value = password, onValueChange = {password = it},
    label = { Text("Masukkan Password")},
    visualTransformation = PasswordVisualTransformation(),
    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )
}

@Composable
fun LoginButton(){
    Button(onClick = {
                     Toast.makeText(MainActivity(), "Tombol Login sudah ditekan", Toast.LENGTH_SHORT).show()
    },
        Modifier
            .padding(top = 10.dp)
            .width(280.dp)) {
     Text(text = "Login", fontWeight = FontWeight.Bold, fontSize = 20.sp)
    }
}

@Composable
fun gambar() {
    Card(
        modifier = Modifier.size(148.dp),
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
