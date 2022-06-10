package com.example.compose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.material.Icon
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.model.Movie
import com.example.compose.repository.MovieRepository
import com.example.compose.ui.theme.ComposeTheme

class MenuActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTheme{
                val movieRepository = MovieRepository()
                val getAllData = movieRepository.getAllData()
                
                Column() {
                    Row(Modifier.padding(8.dp)){
                        Text(text = "Selamat datang Ardan")
                        personIcon()
                    }
                    LazyColumn(contentPadding = PaddingValues(all = 12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)){
                        items(items = getAllData){ Movie ->
                            CustomItem(movie = Movie)
                        }
                    }
                }
                

            }
        }
    }
}

@Composable
fun personIcon(){
    val mContext = LocalContext.current
    Icon(
        painter = painterResource(id = R.drawable.ic_baseline_person_24),
        contentDescription = "",
        modifier = Modifier
            .padding(start = 188.dp)
            .size(28.dp)
            .clickable(
                enabled = true,
                onClickLabel = "Clickable image",
                onClick = {
                    mContext.startActivity(Intent(mContext, UpdateProfilActivity::class.java))
                }
            )
    )
}