# BarChart3D-Compose
BarChart3D-Compose


# Example :

https://github.com/RankUp-Hassen/BarChart3D/assets/140910656/e7bb73e7-1daa-4aae-b447-e4d95ff005f8




[![](https://jitpack.io/v/RankUp-Hassen/BarChart3D-Compose.svg)](https://jitpack.io/#RankUp-Hassen/BarChart3D-Compose)

To get a Git project into your build:

# Step 1. Add the JitPack repository to your build file


Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
# Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.RankUp-Hassen:BarChart3D:1.1'
	}

 if implementation don't working u can Download Releases from here :
 	https://github.com/RankUp-Hassen/BarChart3D/archive/refs/tags/1.1.zip
 	
# Step 3 . Use Code

	 BarChart3D(
                modifier = Modifier.fillMaxSize(),
                enabledAnimation = true ,
                animationDuration = 3000,
                primaryColor = Color.Red,
                value = 75,
                description ="description" ,
                showDescription =true ,
                descriptionColor = Color.Black,
                maxHeight = 500.dp ,
                maxWidth = 40.dp
            )

# Example of use BarChart3D
	
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
                		enabledAnimation = startAnimation ,
                		animationDuration = 3000,
                		primaryColor = input.color,
                		value = input.value,
                		description =input.description ,
                		showDescription =showDescription ,
                		descriptionColor = Color.Black,
                		maxHeight = 500.dp ,
                		maxWidth = 40.dp
            		)
        	}
    	}
	}

	data class BarchartInput(
    		val value:Int,
    		val description:String,
    		val color:Color
	)



	
	



