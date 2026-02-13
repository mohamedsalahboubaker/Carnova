package com.salah.carnova

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.salah.carnova.navigation.AppNavGraph
import com.salah.carnova.ui.feature.intro.home.HomeScreen
import com.salah.carnova.ui.theme.CarNovaTheme
import com.salah.carnova.viewModel.CarViewModel
import com.salah.carnova.viewModel.CategoryViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            AppNavGraph()

        }
    }
}

