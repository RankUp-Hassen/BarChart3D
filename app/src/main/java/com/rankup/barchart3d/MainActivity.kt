package com.rankup.barchart3d

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rankup.barchart3d.ui.theme.BarChart3DTheme
import com.rankup.barchart3d_compose.BarChart3D

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {


            BarChart3DTheme {
                var showDescription by remember {
                    mutableStateOf(false)
                }

                var start by remember {
                    mutableStateOf(false)
                }


                val inputList=listOf(
                    BarchartInput(100,"Kotlin", colorResource(id = R.color.purple_500)),
                    BarchartInput(75,"Swift", Color.Red),
                    BarchartInput(50,"Ruby", Color.Green),
                    BarchartInput(30,"Cobol", colorResource(id = R.color.purple_200)),
                    BarchartInput(20,"C++", Color.Blue),
                    BarchartInput(10,"C#", Color.Yellow),

                    )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(30.dp),
                    verticalArrangement = Arrangement.Top
                ) {
                    Text(
                        "This is BarChart3D Library",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = 30.sp,
                        textAlign = TextAlign.Center,
                    )

                }


                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(30.dp),
                    verticalArrangement = Arrangement.Bottom
                ){

                    if (start){
                        BarChart(
                            inputList,
                            modifier = Modifier.fillMaxWidth(),
                            showDescription = showDescription,
                            startAnimation = true
                        )

                    }

                    Row(

                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 50.dp)
                    ){
                        Text(
                            "Show description",
                            color = Color.Black,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(end = 20.dp)

                        )
                        Switch(
                            checked = showDescription,
                            onCheckedChange = {
                                showDescription = it
                            },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = Color.White,
                                uncheckedThumbColor = Color.White
                            )
                        )

                    }
                    Button(onClick = { start=!start }) {
                        
                        Text(text = if (start) "Stop" else "Start")


                    }



                }
            }
        }
    }
}




@Composable
fun BarChart(
    inputList:List<BarchartInput>,
    modifier: Modifier = Modifier,
    showDescription:Boolean,
    startAnimation: Boolean
) {
    Row(
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
    ){

        inputList.forEach { input ->

            BarChart3D(
                modifier = Modifier.fillMaxSize(),
                primaryColor = input.color,
                value = input.value,
                description = input.description,
                showDescription = showDescription,
                enabledAnimation = startAnimation
            )
        }
    }
}

data class BarchartInput(
    val value:Int,
    val description:String,
    val color:Color
)

