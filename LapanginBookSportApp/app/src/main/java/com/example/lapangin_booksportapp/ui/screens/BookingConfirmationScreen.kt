package com.example.lapangin_booksportapp.ui.screens

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.lapangin_booksportapp.ui.theme.Border
import com.example.lapangin_booksportapp.ui.theme.DMSans
import com.example.lapangin_booksportapp.ui.theme.Dark
import com.example.lapangin_booksportapp.ui.theme.Lime
import com.example.lapangin_booksportapp.ui.theme.Surface2
import com.example.lapangin_booksportapp.ui.theme.Surface3
import com.example.lapangin_booksportapp.ui.theme.Syne
import com.example.lapangin_booksportapp.ui.theme.TextMuted
import com.example.lapangin_booksportapp.ui.theme.TextPrimary

@Composable
fun BookingConfirmationScreen(
    onBackToHomeClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Dark)
    ) {
        // Confetti Area Background
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(Lime.copy(alpha = 0.12f), Color.Transparent),
                        center = Offset(Float.POSITIVE_INFINITY / 2, 0f), // Simulating top center
                        radius = 800f
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(60.dp))

            // Animated Check Ring
            val infiniteTransition = rememberInfiniteTransition()
            val rotation by infiniteTransition.animateFloat(
                initialValue = 0f,
                targetValue = 360f,
                animationSpec = infiniteRepeatable(
                    animation = tween(12000, easing = LinearEasing),
                    repeatMode = RepeatMode.Restart
                ),
                label = "ring_rotation"
            )

            Box(
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape)
                    .background(Lime.copy(alpha = 0.1f))
                    .border(1.5.dp, Lime.copy(alpha = 0.3f), CircleShape)
                    .padding(bottom = 14.dp),
                contentAlignment = Alignment.Center
            ) {
                // Dashed border spinning
                Canvas(modifier = Modifier.matchParentSize().rotate(rotation)) {
                    drawCircle(
                        color = Lime.copy(alpha = 0.15f),
                        radius = size.minDimension / 2 + 8.dp.toPx(), // Inflate by 8dp to match inset -8px
                        style = androidx.compose.ui.graphics.drawscope.Stroke(
                            width = 1.dp.toPx(),
                            pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                        )
                    )
                }

                Text(
                    text = "✔️",
                    fontSize = 30.sp,
                    modifier = Modifier.padding(top = 14.dp) // Adjust visual alignment
                )
            }
            
            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = "Pemesanan Berhasil!",
                fontFamily = Syne,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 22.sp,
                color = TextPrimary,
                letterSpacing = (-0.03).em,
                textAlign = TextAlign.Center
            )

            Text(
                text = "Cek email kamu untuk detail selengkapnya.",
                fontFamily = DMSans,
                fontSize = 12.sp,
                color = TextMuted,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 4.dp)
            )

            // Ticket Box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, start = 18.dp, end = 18.dp)
            ) {
                // Main Ticket Content
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .background(Surface2)
                        .border(1.dp, Border, RoundedCornerShape(20.dp))
                ) {
                    // Ticket Top
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 14.dp, bottom = 12.dp, start = 18.dp, end = 18.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(42.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(
                                    brush = Brush.linearGradient(
                                        colors = listOf(Color(0xFF0D1F12), Color(0xFF162B1A))
                                    )
                                )
                                .border(1.dp, Color.White.copy(alpha = 0.05f), RoundedCornerShape(12.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "⚽", fontSize = 20.sp)
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column {
                            Text(
                                text = "GOR Manyar",
                                fontFamily = Syne,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 15.sp,
                                color = TextPrimary,
                                letterSpacing = (-0.02).em,
                                modifier = Modifier.padding(bottom = 2.dp)
                            )
                            Text(
                                text = "FUTSAL",
                                fontFamily = DMSans,
                                fontWeight = FontWeight.Medium,
                                fontSize = 11.sp,
                                color = Lime
                            )
                        }

                        Spacer(modifier = Modifier.weight(1f))

                        Column(horizontalAlignment = Alignment.End) {
                            Text(
                                text = "BOOKING ID",
                                fontFamily = Syne,
                                fontSize = 10.sp,
                                color = TextMuted
                            )
                            Text(
                                text = "LPN-8829",
                                fontFamily = DMSans,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                color = TextPrimary
                            )
                        }
                    }

                    // Dashed Divider using Canvas
                    Canvas(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                    ) {
                        drawLine(
                            color = Border,
                            start = Offset(0f, 0f),
                            end = Offset(size.width, 0f),
                            strokeWidth = 1.dp.toPx(),
                            pathEffect = PathEffect.dashPathEffect(floatArrayOf(15f, 10f), 0f)
                        )
                    }

                    // Ticket Bottom
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp, bottom = 14.dp, start = 18.dp, end = 18.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Row(modifier = Modifier.fillMaxWidth()) {
                            TicketDataRow(label = "TANGGAL", value = "26 Apr 2025", modifier = Modifier.weight(1f))
                            TicketDataRow(label = "WAKTU", value = "19:00 - 21:00", modifier = Modifier.weight(1f))
                        }
                        Row(modifier = Modifier.fillMaxWidth()) {
                            TicketDataRow(label = "PEMAIN", value = "Huraira S.", modifier = Modifier.weight(1f))
                            TicketDataRow(label = "TOTAL", value = "Rp160.000", modifier = Modifier.weight(1f))
                        }
                    }
                    
                    // Barcode Area
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 18.dp)
                            .padding(bottom = 18.dp)
                            .height(44.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Surface3),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(2.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Generating dummy barcode lines
                            val barHeights = listOf(20f, 30f, 15f, 40f, 25f, 35f, 10f, 20f, 35f, 40f, 20f, 15f, 30f, 25f, 40f, 10f, 20f, 35f, 15f, 30f, 20f, 40f, 25f)
                            barHeights.forEach { height ->
                                Box(
                                    modifier = Modifier
                                        .width(2.dp)
                                        .height(height.dp)
                                        .clip(RoundedCornerShape(1.dp))
                                        .background(Color(0xFF666666))
                                )
                            }
                        }
                    }
                }

                // Left Cutout
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .offset(x = (-10).dp) // Center horizontally over the edge
                        .size(20.dp)
                        .clip(CircleShape)
                        .background(Dark)
                        .border(1.dp, Border, CircleShape)
                )

                // Right Cutout
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .offset(x = 10.dp) // Center horizontally over the edge
                        .size(20.dp)
                        .clip(CircleShape)
                        .background(Dark)
                        .border(1.dp, Border, CircleShape)
                )
            }

            // Action Buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 14.dp, start = 18.dp, end = 18.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // Secondary Button
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(14.dp))
                        .background(Surface2)
                        .border(1.dp, Border, RoundedCornerShape(14.dp))
                        .clickable { onBackToHomeClick() }
                        .padding(12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Kembali ke Beranda",
                        fontFamily = Syne,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = TextPrimary
                    )
                }

                // Primary Button
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(14.dp))
                        .background(Lime)
                        .clickable { /* TODO: Download Ticket */ }
                        .padding(12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Unduh Tiket",
                        fontFamily = Syne,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = Dark
                    )
                }
            }
        }
    }
}

@Composable
fun TicketDataRow(label: String, value: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = label,
            fontFamily = DMSans,
            fontWeight = FontWeight.SemiBold,
            fontSize = 9.5.sp,
            color = TextMuted,
            letterSpacing = 0.08.em,
            modifier = Modifier.padding(bottom = 3.dp)
        )
        Text(
            text = value,
            fontFamily = Syne,
            fontWeight = FontWeight.Bold,
            fontSize = 13.sp,
            color = TextPrimary,
            letterSpacing = (-0.01).em
        )
    }
}
