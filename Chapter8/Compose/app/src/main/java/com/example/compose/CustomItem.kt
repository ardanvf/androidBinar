package com.example.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.model.Movie
import androidx.compose.foundation.Image
import androidx.compose.material.Card
import androidx.compose.ui.graphics.painter.Painter

@Composable
fun CustomItem(movie: Movie){
    Column(modifier = Modifier
        .background(Color.LightGray)
        .fillMaxWidth()
        .padding(24.dp)){
        Text(
            text = movie.tittle,
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "${movie.year}",
            color = Color.Black,
            fontSize = 8.sp,
            fontWeight = FontWeight.Normal
        )
        Text(
            text = movie.description,
            color = Color.Black,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal
        )
        Text(
            text = "Rating : ${movie.rate}",
            color = Color.Black,
            fontSize = 10.sp,
            fontWeight = FontWeight.Normal
        )
    }
}