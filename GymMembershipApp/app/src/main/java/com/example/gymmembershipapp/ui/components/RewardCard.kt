package com.example.gymmembershipapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymmembershipapp.domain.Reward
import com.example.gymmembershipapp.ui.theme.AccentGreen
import com.example.gymmembershipapp.ui.theme.DividerColor
import com.example.gymmembershipapp.ui.theme.SurfaceDark
import com.example.gymmembershipapp.ui.theme.SurfaceElevated
import com.example.gymmembershipapp.ui.theme.TextMuted
import com.example.gymmembershipapp.ui.theme.TextSecondary

@Composable
fun RewardCard(
    reward: Reward,
    currentPoints: Int,
    onRedeem: () -> Unit,
    modifier: Modifier = Modifier
) {
    val canRedeem = currentPoints >= reward.pointsRequired
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceDark),
        border = BorderStroke(1.dp, DividerColor)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(SurfaceElevated),
                contentAlignment = Alignment.Center
            ) {
                Text(text = reward.emoji, fontSize = 24.sp)
            }
            Spacer(Modifier.width(14.dp))
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    text = reward.name,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = reward.description,
                    color = TextSecondary,
                    fontSize = 12.sp
                )
                Spacer(Modifier.size(4.dp))
                Text(
                    text = "${reward.pointsRequired} pts",
                    color = if (canRedeem) AccentGreen else TextMuted,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(Modifier.width(8.dp))
            Button(
                onClick = onRedeem,
                enabled = canRedeem,
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AccentGreen,
                    contentColor = Color.Black,
                    disabledContainerColor = SurfaceElevated,
                    disabledContentColor = TextMuted
                )
            ) {
                Text(text = "Redeem", fontWeight = FontWeight.Bold)
            }
        }
    }
}
