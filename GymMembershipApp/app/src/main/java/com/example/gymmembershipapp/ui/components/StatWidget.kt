package com.example.gymmembershipapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymmembershipapp.ui.theme.AccentGreen
import com.example.gymmembershipapp.ui.theme.DividerColor
import com.example.gymmembershipapp.ui.theme.SurfaceDark
import com.example.gymmembershipapp.ui.theme.TextSecondary

/** Big number + small label, e.g. "12 / MEMBERS". */
@Composable
fun StatWidget(
    value: String,
    label: String,
    modifier: Modifier = Modifier,
    valueColor: Color = AccentGreen
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceDark),
        border = androidx.compose.foundation.BorderStroke(1.dp, DividerColor)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 18.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = value,
                color = valueColor,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = label.uppercase(),
                color = TextSecondary,
                fontSize = 11.sp,
                letterSpacing = 1.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}
