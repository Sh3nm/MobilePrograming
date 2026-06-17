package com.example.gymmembershipapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QrCode2
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymmembershipapp.data.local.entity.MemberEntity
import com.example.gymmembershipapp.domain.getMemberLevel
import com.example.gymmembershipapp.ui.theme.AccentGreen
import com.example.gymmembershipapp.ui.theme.CardGradientEnd
import com.example.gymmembershipapp.ui.theme.CardGradientStart
import com.example.gymmembershipapp.ui.theme.MonoTextStyle
import com.example.gymmembershipapp.ui.theme.TextSecondary

@Composable
fun MembershipCard(
    member: MemberEntity,
    modifier: Modifier = Modifier
) {
    val level = getMemberLevel(member.points)
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(
                Brush.linearGradient(listOf(CardGradientStart, CardGradientEnd))
            )
            .border(1.dp, AccentGreen.copy(alpha = 0.33f), RoundedCornerShape(16.dp))
            .padding(20.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // Brand row
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(RoundedCornerShape(2.dp))
                        .background(AccentGreen)
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = "FITTRACK",
                    color = AccentGreen,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 3.sp,
                    fontSize = 13.sp
                )
            }

            Spacer(Modifier.height(20.dp))

            Text(
                text = member.name.uppercase(),
                style = MaterialTheme.typography.displaySmall,
                color = Color.White
            )
            Text(
                text = "ID: ${member.memberId}",
                style = MonoTextStyle,
                color = TextSecondary
            )

            Spacer(Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = null,
                            tint = AccentGreen,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(Modifier.width(6.dp))
                        Text(
                            text = "${member.points} POINTS",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    }
                    Spacer(Modifier.height(10.dp))
                    LevelBadge(level = level)
                }

                // QR placeholder
                Box(
                    modifier = Modifier
                        .size(72.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .border(1.dp, AccentGreen.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
                        .background(Color.Black.copy(alpha = 0.3f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.QrCode2,
                        contentDescription = "QR Code",
                        tint = AccentGreen.copy(alpha = 0.8f),
                        modifier = Modifier.size(48.dp)
                    )
                }
            }
        }
    }
}
