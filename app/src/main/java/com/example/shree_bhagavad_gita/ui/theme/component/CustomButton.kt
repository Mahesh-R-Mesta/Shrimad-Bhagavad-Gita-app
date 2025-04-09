package com.example.shree_bhagavad_gita.ui.theme.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.example.shree_bhagavad_gita.ui.theme.orange60


@Composable
fun CustomButton(label:String,onClick:()->Unit,modifier:Modifier=Modifier) {
    modifier.padding(1.dp)
    val buttonColor = ButtonColors(containerColor = orange60, contentColor = Color.White, disabledContentColor = Color.White, disabledContainerColor =  orange60)
    Button(onClick = onClick, modifier =  modifier, shape = RoundedCornerShape(10.dp), colors = buttonColor,) {
        Text(text = label, style = TextStyle(color = Color.White, fontSize = TextUnit(14F, type = TextUnitType.Sp,), fontWeight = FontWeight.Medium))
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonPreview() {
    CustomButton("Get Stared", onClick = {})
}