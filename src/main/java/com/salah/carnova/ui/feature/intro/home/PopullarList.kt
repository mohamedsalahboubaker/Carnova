package com.salah.carnova.ui.feature.intro.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.salah.carnova.R
import com.salah.carnova.domain.CarModel

@Composable
fun popularList(
    cars:List<CarModel>,
    onCarClick:(CarModel)->Unit
){
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier= Modifier.padding(start = 8.dp, end=8.dp, bottom = 130.dp)
            .fillMaxWidth()
            .height(600.dp)

    ) {
        items(cars){car->
            Card(modifier=Modifier.padding(8.dp)
                .fillMaxWidth()
                .background(color= colorResource(R.color.grey))
                .clickable{onCarClick(car)},
                shape = RoundedCornerShape(15.dp),
                elevation= CardDefaults.cardElevation(8.dp)




            ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(bottom = 8.dp)
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(car.picUrl), contentDescription = null,
                        modifier = Modifier.height(130.dp)
                            .fillMaxWidth(),
                        contentScale = ContentScale.Crop
                    )

                    Text(
                        car.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(top = 8.dp)

                    )
                    Text(
                        "$${car.price}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }

        }
    }

}
@Preview(showBackground = true)
@Composable
fun PreviewPopularList() {
    val fakeCars = listOf(
        CarModel(
            title = "Tesla Model S",
            description = "Electric luxury car",
            totalCapacity = "5 seats",
            highestSpeed = "250 km/h",
            engineOutput = "670 hp",
            picUrl = "https://cdn.motor1.com/images/mgl/0ANQJ/s1/tesla-model-s.jpg",
            price = 89999.0,
            rating = 4.8
        ),
        CarModel(
            title = "BMW M3",
            description = "Sport sedan",
            totalCapacity = "5 seats",
            highestSpeed = "280 km/h",
            engineOutput = "480 hp",
            picUrl = "https://www.bmwusa.com/content/dam/bmwusa/M/M3/2023/BMW-MY23-M3-CS-Performance-Page-1-Desktop.jpg",
            price = 73999.0,
            rating = 4.6
        )
    )
    popularList(cars = fakeCars, onCarClick = {})
}

