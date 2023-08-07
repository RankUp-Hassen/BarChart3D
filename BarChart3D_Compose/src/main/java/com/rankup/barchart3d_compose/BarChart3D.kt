package com.rankup.barchart3d_compose

import android.graphics.Paint
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun BarChart3D(
    modifier: Modifier = Modifier,
    enabledAnimation: Boolean = true,
    animationDuration: Int = 3000,
    primaryColor: Color,
    value:Int,
    description: String,
    showDescription: Boolean,
    descriptionColor: Color = Color.Black,
    maxHeight: Dp = 500.dp,
    maxWidth: Dp = 40.dp

) {



    var expanded by remember { mutableStateOf(false) }

    val targetNumber = if (expanded) value else 0
    val animatedNumber by animateIntAsState(
        targetValue = targetNumber,
        animationSpec = tween(durationMillis = animationDuration)
    )



    val targetHeight = if (expanded) (maxHeight/100)*value else 0.dp
    val animatedHeight by animateDpAsState(
        targetValue = targetHeight,
        animationSpec = tween(durationMillis = animationDuration)
    )




    Box(
        modifier = Modifier
            .height(if (enabledAnimation) animatedHeight else (maxHeight/100)*value )
            .width(maxWidth),
        contentAlignment = Alignment.BottomCenter


    ){
        Canvas(
            modifier = modifier

        ){

            expanded=true
            val width = size.width
            val height = size.height
            val barWidth = width / 5 * 3
            val barHeight = height /8*7.5
            val barHeight3DPart = height - barHeight
            val barWidth3DPart = (width - barWidth)*(height*0.001f)

            var path = Path().apply {
                moveTo(0f,height)
                lineTo(barWidth,height)
                lineTo(barWidth, (height-barHeight).toFloat())
                lineTo(0f, (height-barHeight).toFloat())
                close()
            }
            drawPath(
                path,
                brush = Brush.linearGradient(
                    colors = listOf(Color.Gray, primaryColor)
                )
            )
            path = Path().apply {
                moveTo(barWidth, (height-barHeight).toFloat())
                lineTo(barWidth3DPart+barWidth,0f)
                lineTo(barWidth3DPart+barWidth, barHeight.toFloat())
                lineTo(barWidth,height)
                close()
            }
            drawPath(
                path,
                brush = Brush.linearGradient(
                    colors = listOf(primaryColor, Color.Gray)
                )
            )
            path = Path().apply {
                moveTo(0f, barHeight3DPart.toFloat())
                lineTo(barWidth, barHeight3DPart.toFloat())
                lineTo(barWidth+barWidth3DPart,0f)
                lineTo(barWidth3DPart,0f)
                close()
            }
            drawPath(
                path,
                brush = Brush.linearGradient(
                    colors = listOf(Color.Gray, primaryColor)
                )
            )
            drawContext.canvas.nativeCanvas.apply {
                drawText(
                    if (enabledAnimation) "$animatedNumber %" else "$value %",
                    barWidth/5f,
                    height + 55f,
                    Paint().apply {
                        this.color = descriptionColor.toArgb()
                        textSize = 11.dp.toPx()
                        isFakeBoldText = true
                    }
                )
            }
            if(showDescription){
                drawContext.canvas.nativeCanvas.apply {
                    rotate(-50f, pivot = Offset(barWidth3DPart-20,0f)){
                        drawText(
                            description,
                            0f,
                            0f,
                            Paint().apply {
                                this.color = descriptionColor.toArgb()
                                textSize = 14.dp.toPx()
                                isFakeBoldText = true
                            }
                        )
                    }
                }
            }
        }
    }




}
