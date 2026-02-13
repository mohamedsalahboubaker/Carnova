package com.salah.carnova.ui.feature.intro.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.salah.carnova.R
import com.salah.carnova.ui.component.TopBar

@Composable
fun DetailHeader(
    picUrl:String?,
    onBack:()->Unit,
    onFav:()->Unit,
    modifier: Modifier =Modifier
){
    Box(
        modifier=Modifier.fillMaxWidth()
            .height(500.dp)
            .background(colorResource(id= R.color.black))
    ){
        TopBar(title="Car Detail",backIconRes=R.drawable.back1,
            trailingIconRes = R.drawable.fav_icon,
            onBack = onBack,
            onTrailingClick = onFav,
            titleColorRes = R.color.white,
            modifier=Modifier.fillMaxWidth().statusBarsPadding())

        AsyncImage(
            model=picUrl,
            contentDescription = null,
            contentScale = ContentScale.Inside,
            placeholder = painterResource(R.drawable.intro_car),
            modifier=Modifier.align(Alignment.BottomCenter)
                .padding(start=32.dp,bottom=15.dp,end= 32.dp)
                .fillMaxSize()
                .height(220.dp)
        )

    }

}