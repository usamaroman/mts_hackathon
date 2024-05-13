package by.eapp.mts.presentation.utils

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp

@Composable
fun CustomText(
    modifier: Modifier = Modifier,
    text: String = "",
    textAlign: TextAlign = TextAlign.Center,
    fontSize: Int = 14,
    color: Color = Color.Black,
    fontFamily: FontFamily = FontFamily.Default,
    fontStyle: FontStyle = FontStyle.Normal,
    fontWeight: FontWeight = FontWeight.Normal,
    textDecoration: TextDecoration = TextDecoration.None,
) {
    Text(
        text = text,
        textAlign = textAlign,
        fontSize = fontSize.sp,
        fontFamily = fontFamily,
        fontStyle = fontStyle,
        fontWeight = fontWeight,
        color = color,
        modifier = modifier,
        textDecoration = textDecoration
    )
}