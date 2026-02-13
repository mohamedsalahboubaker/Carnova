package com.salah.carnova.ui.feature.intro.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.salah.carnova.domain.CarModel
import com.salah.carnova.viewModel.CarViewModel
import com.salah.carnova.viewModel.CategoryViewModel

@Composable

fun HomeScreen(onProfileClick:()->Unit,onCarClick:(CarModel)-> Unit, carViewModel: CarViewModel, categoryViewModel: CategoryViewModel){
    val categories by categoryViewModel.categories
    val isLoadingCategory by categoryViewModel.isLoading
    val cars by carViewModel.cars
    val isLoadingCars by carViewModel.isLoading



    Box (modifier = Modifier.fillMaxSize()){
        LazyColumn(modifier = Modifier.fillMaxSize()
            .background(Color(color=0xffeffefef))) {

            item {
                HeaderSection(username = "salah bbk", onBellClick = {})
            }
            item { SearchSection() }

            item { if(isLoadingCategory){
                Box(modifier=Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
                    CircularProgressIndicator()
                }
            }else
            {
                CategoryList(categories)

            }

            }

            item{Spacer( Modifier.height(16.dp))
                Column(modifier=Modifier.background(color = Color.White,shape=RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp))

                ) {
                    Row(modifier=Modifier.padding(top=24.dp, start = 16.dp, end = 16.dp)
                        .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("Popular Cars", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                        Text("View All", fontSize = 22.sp)

                    }
                    Spacer(Modifier.fillMaxWidth())

                    if(isLoadingCars){
                        Box(modifier=Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center){
                            CircularProgressIndicator(color = Color.Gray)

                        }
                    }else{
                        popularList(cars, onCarClick=onCarClick )
                    }


                }
            }

        }

        BottomNavBar(onProfileClick=onProfileClick,
            modifier=Modifier.align(Alignment.BottomCenter)
                .navigationBarsPadding()
                .padding(horizontal = 16.dp, vertical = 24.dp))


    }
}

