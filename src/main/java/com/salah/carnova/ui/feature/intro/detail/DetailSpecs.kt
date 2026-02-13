package com.salah.carnova.ui.feature.intro.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.salah.carnova.R
import com.salah.carnova.ui.component.SpecCar

@Composable
fun DetailSpecs(totalCapacity: String,highestSpeed:String,engineOutput:String){
    Row(modifier= Modifier.fillMaxWidth()
        .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)){

        SpecCar(R.drawable.sit,"Total \n Capacity",totalCapacity.ifEmpty { "N/A" }, Modifier.weight(1f))
        SpecCar(R.drawable.speed,"Highest \n Speed",highestSpeed.ifEmpty { "N/A" }, Modifier.weight(1f))
        SpecCar(R.drawable.engine,"Engine \n Output", Value = engineOutput.ifEmpty { "N/A" }, Modifier.weight(1f))

    }
}