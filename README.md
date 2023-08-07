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
	        implementation 'com.github.RankUp-Hassen:BarChart3D:Tag'
	}


# Step 3 . Useing Code

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

     

