package com.salah.carnova.ui.component

import android.R.attr.icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.salah.carnova.R

@Composable
fun SettingRow(Icon:Int,
               title:String,
               modifier:Modifier= Modifier,
               onClick:()->Unit={}
){

    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.height(60.dp)
            .fillMaxWidth()
            .background(color = colorResource(R.color.grey))
            .clickable{onClick()}.padding((16.dp))){
        Image(painterResource(Icon), contentDescription = null)
            Spacer(Modifier.width(16.dp))

        Text(title, color = colorResource(R.color.black),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyLarge)


    }
}