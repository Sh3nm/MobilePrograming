package com.example.gymmembershipapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import com.example.gymmembershipapp.data.local.entity.WorkoutEntity
import com.example.gymmembershipapp.domain.WorkoutType
import com.example.gymmembershipapp.ui.theme.AccentGreen
import com.example.gymmembershipapp.ui.theme.DividerColor
import com.example.gymmembershipapp.ui.theme.SurfaceDark
import com.example.gymmembershipapp.ui.theme.TextSecondary

@Composable
fun WorkoutHistoryItem(
    workout: WorkoutEntity,
    modifier: Modifier = Modifier
) {
    val type = WorkoutType.fromLabel(workout.workoutType)
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceDark),
        border = BorderStroke(1.dp, DividerColor)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = type.emoji, fontSize = 22.sp)
            Spacer(Modifier.width(12.dp))
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    text = "${workout.workoutType} • ${workout.duration} mnt",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = workout.date,
                    color = TextSecondary,
                    fontSize = 12.sp
                )
            }
            Text(
                text = "+${workout.pointEarned} pts",
                color = AccentGreen,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
