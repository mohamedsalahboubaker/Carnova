package com.salah.carnova.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.salah.carnova.R

@Composable
fun SpecCar(iconRes:Int,title:String,Value:String,modifier: Modifier =Modifier){

    val displayValue = if(Value.isEmpty()) "N/A" else Value
    Column(modifier=Modifier.clip(RoundedCornerShape(topStart =50.dp, topEnd = 50.dp))
        .background(color = colorResource(id= R.color.grey))
        .padding(top=16.dp,bottom=12.dp)){
        Image(painterResource(iconRes), contentDescription = null,
            modifier=Modifier.padding(start=15.dp))

        Text(text=title,
            color = colorResource(id=R.color.black),
            fontSize = 15.sp,
            modifier=Modifier.padding(start=16.dp))

        Text(text=displayValue,
            color = colorResource(id=R.color.black),
            fontWeight = FontWeight.Bold,
            modifier=Modifier.padding(start=16.dp,top=8.dp,end=16.dp))
    }
}