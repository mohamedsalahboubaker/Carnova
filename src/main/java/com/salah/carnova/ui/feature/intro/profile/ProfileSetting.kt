package com.salah.carnova.ui.feature.intro.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.salah.carnova.R
import com.salah.carnova.ui.component.SettingRow

@Composable
@Preview
fun ProfileSetting(){
    Column { SettingRow(R.drawable.review,"rating and review",
        Modifier.padding(horizontal = 16.dp, vertical = 16.dp))

        SettingRow(R.drawable.contact,"Contact Support",
            Modifier.padding(horizontal = 16.dp, vertical = 16.dp))

        SettingRow(R.drawable.social,"Social Media Link",
            Modifier.padding(horizontal = 16.dp, vertical = 16.dp)) }
}