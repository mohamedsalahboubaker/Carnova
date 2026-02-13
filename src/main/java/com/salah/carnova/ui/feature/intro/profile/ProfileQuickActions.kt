package com.salah.carnova.ui.feature.intro.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.salah.carnova.R
import com.salah.carnova.ui.component.QuickActionCard
@Preview
@Composable
fun ProfileQuickActions(){
    Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(15.dp)) {

        QuickActionCard(R.drawable.maintain,"Maintain", Modifier.weight(1f))
        QuickActionCard(R.drawable.autopart,"auto part", Modifier.weight(1f))
        QuickActionCard(R.drawable.drivingskill,"driving skill", Modifier.weight(1f))

    }
}