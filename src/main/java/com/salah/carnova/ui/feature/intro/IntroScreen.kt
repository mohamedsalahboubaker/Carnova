package com.salah.carnova.ui.feature.intro

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.salah.carnova.R

@Composable

fun IntroScreen(navToMain:()->Unit){
    Box(modifier=Modifier.fillMaxSize().background(color = Color.Black)){

        Column(modifier=Modifier.fillMaxSize()
            .padding(top=48.dp, bottom = 32.dp),
            verticalArrangement = Arrangement.SpaceBetween) {

            Column {
                Text("Easy Way to\n buy\n your dream car",
                    modifier = Modifier.padding(horizontal = 24.dp)
                        .statusBarsPadding(),
                    color = Color.White,
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold)

                Spacer(modifier=Modifier.height(24.dp))

                Text("\nExplore top car brands," +
                        "\n compare prices in just a few clicks," +
                        "\n and find the car that matches your style." +
                        "\n With Carnova, drive your dream car today.",
                    color = Color.White, modifier = Modifier.padding(horizontal=20.dp),
                    fontSize = 18.sp, lineHeight =24.sp

                )

            }
            Column {
                Image(painter = painterResource(id= R.drawable.intro_car),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth().heightIn(min=200.dp)
                    )
                Spacer(modifier=Modifier.height(24.dp))

                Button(
                    onClick =navToMain ,
                    modifier=Modifier.fillMaxWidth()
                    .padding(horizontal = 24.dp)
                        .height(60.dp),
                    shape = RoundedCornerShape(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black
                    )) {
                    Text("Start",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold)

                }
            }


        }

    }
}
@Preview(showBackground = true)
@Composable
fun IntroScreenPreview() {
    IntroScreen(navToMain = {}) // ici on passe une lambda vide
}
