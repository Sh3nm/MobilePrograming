package com.example.lapangin_booksportapp.ui.screens

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.lapangin_booksportapp.ui.theme.Border
import com.example.lapangin_booksportapp.ui.theme.DMSans
import com.example.lapangin_booksportapp.ui.theme.Dark
import com.example.lapangin_booksportapp.ui.theme.Lime
import com.example.lapangin_booksportapp.ui.theme.StarYellow
import com.example.lapangin_booksportapp.ui.theme.Surface2
import com.example.lapangin_booksportapp.ui.theme.Surface3
import com.example.lapangin_booksportapp.ui.theme.Syne
import com.example.lapangin_booksportapp.ui.theme.TextMuted
import com.example.lapangin_booksportapp.ui.theme.TextPrimary

@Composable
fun VenueDetailScreen(
    onBackClick: () -> Unit,
    onBookClick: () -> Unit = {}
) {
    val selectedSlots = remember { mutableStateListOf<String>() }
    val pricePerSlot = 80000

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Dark)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 90.dp) // Space for bottom bar
        ) {
            // Hero Section
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(Color(0xFF0D1F12), Color(0xFF162B1A))
                        )
                    )
            ) {
                // Background Emoji Blurred
                Text(
                    text = "⚽",
                    fontSize = 80.sp,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(end = 10.dp)
                        .offset(y = 10.dp)
                        .alpha(0.2f)
                        .blur(1.dp)
                )

                // Main Emoji
                Text(
                    text = "⚽",
                    fontSize = 56.sp,
                    modifier = Modifier.align(Alignment.Center)
                )

                // Back Button
                Box(
                    modifier = Modifier
                        .padding(top = 42.dp, start = 16.dp)
                        .size(34.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.Black.copy(alpha = 0.5f))
                        .border(1.dp, Color.White.copy(alpha = 0.1f), RoundedCornerShape(10.dp))
                        .clickable { onBackClick() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = "Back",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                }

                // Favorite Button
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = 42.dp, end = 16.dp)
                        .size(34.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.Black.copy(alpha = 0.5f))
                        .border(1.dp, Color.White.copy(alpha = 0.1f), RoundedCornerShape(10.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                }

                // Buka Sekarang Badge
                val infiniteTransition = rememberInfiniteTransition(label = "pulse")
                val alphaPulse by infiniteTransition.animateFloat(
                    initialValue = 1f,
                    targetValue = 0.3f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(1500),
                        repeatMode = RepeatMode.Reverse
                    ),
                    label = "alphaPulse"
                )

                Row(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(bottom = 12.dp, start = 16.dp)
                        .clip(RoundedCornerShape(100.dp))
                        .background(Lime.copy(alpha = 0.15f))
                        .border(1.dp, Lime.copy(alpha = 0.3f), RoundedCornerShape(100.dp))
                        .padding(horizontal = 10.dp, vertical = 3.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(5.dp)
                            .clip(CircleShape)
                            .background(Lime)
                            .alpha(alphaPulse)
                    )
                    Text(
                        text = "Buka Sekarang",
                        fontFamily = DMSans,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 10.sp,
                        color = Lime
                    )
                }
            }

            // Detail Body
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "GOR Manyar",
                    fontFamily = Syne,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 20.sp,
                    color = TextPrimary,
                    letterSpacing = (-0.02).em,
                    modifier = Modifier.padding(bottom = 4.dp)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 14.dp)
                ) {
                    Text(text = "📍", fontSize = 10.sp)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "Sukolilo", fontFamily = DMSans, fontSize = 11.sp, color = TextMuted)
                    
                    Text(text = " • ", fontSize = 11.sp, color = Border, modifier = Modifier.padding(horizontal = 4.dp))
                    
                    Icon(Icons.Default.Star, contentDescription = null, tint = StarYellow, modifier = Modifier.size(12.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "4.8", fontFamily = DMSans, fontWeight = FontWeight.Bold, fontSize = 11.sp, color = StarYellow)
                    
                    Text(text = " • ", fontSize = 11.sp, color = Border, modifier = Modifier.padding(horizontal = 4.dp))
                    
                    Text(text = "124 ulasan", fontFamily = DMSans, fontSize = 11.sp, color = TextMuted)
                }

                // Info Chips
                Row(
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    InfoChip("🏟️", "4 Lapangan")
                    InfoChip("🚿", "Shower")
                    InfoChip("🅿️", "Parkir")
                    InfoChip("💡", "LED")
                }

                // Waktu Slots
                Text(
                    text = "Pilih Slot Waktu — Sabtu 26 Apr",
                    fontFamily = Syne,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    color = TextPrimary,
                    letterSpacing = (-0.01).em,
                    modifier = Modifier.padding(bottom = 10.dp)
                )

                val timeSlots = listOf(
                    SlotData("07:00", SlotState.BOOKED),
                    SlotData("08:00", SlotState.BOOKED),
                    SlotData("09:00", SlotState.AVAILABLE),
                    SlotData("10:00", SlotState.AVAILABLE),
                    SlotData("11:00", SlotState.AVAILABLE),
                    SlotData("12:00", SlotState.BOOKED),
                    SlotData("13:00", SlotState.AVAILABLE),
                    SlotData("15:00", SlotState.AVAILABLE),
                    SlotData("17:00", SlotState.AVAILABLE),
                    SlotData("19:00", SlotState.AVAILABLE), // User selected these in mockup
                    SlotData("20:00", SlotState.AVAILABLE), // User selected these in mockup
                    SlotData("21:00", SlotState.AVAILABLE),
                )

                Column(
                    verticalArrangement = Arrangement.spacedBy(7.dp)
                ) {
                    timeSlots.chunked(4).forEach { rowSlots ->
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(7.dp)
                        ) {
                            rowSlots.forEach { slot ->
                                val isSelected = selectedSlots.contains(slot.time)
                                val currentState = if (isSelected) SlotState.SELECTED else slot.state

                                Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .background(
                                    when (currentState) {
                                        SlotState.SELECTED -> Lime
                                        SlotState.BOOKED -> Surface3
                                        else -> Surface2
                                    }
                                )
                                .border(
                                    width = 1.dp,
                                    color = if (currentState == SlotState.SELECTED) Lime else Border,
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .clickable(enabled = slot.state != SlotState.BOOKED) {
                                    if (isSelected) selectedSlots.remove(slot.time)
                                    else selectedSlots.add(slot.time)
                                }
                                .padding(horizontal = 12.dp, vertical = 7.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = slot.time,
                                fontFamily = DMSans,
                                fontWeight = if (currentState == SlotState.SELECTED) FontWeight.Bold else FontWeight.Medium,
                                fontSize = 11.sp,
                                color = when (currentState) {
                                    SlotState.SELECTED -> Dark
                                    SlotState.AVAILABLE -> TextPrimary
                                    SlotState.BOOKED -> TextMuted
                                },
                                textDecoration = if (currentState == SlotState.BOOKED) androidx.compose.ui.text.style.TextDecoration.LineThrough else null,
                                modifier = Modifier.alpha(if (currentState == SlotState.BOOKED) 0.4f else 1f)
                            )
                        }
                    }
                }
            }
        }
            }
        }

        // Bottom Booking Bar
        val totalPrice = selectedSlots.size * pricePerSlot
        
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(Color(0xF2161616)) // Glassmorphism-like
                .border(1.dp, Border)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Surface2)
                    .border(1.dp, Border, RoundedCornerShape(16.dp))
                    .padding(horizontal = 14.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Total ${selectedSlots.size} jam",
                        fontFamily = DMSans,
                        fontSize = 10.sp,
                        color = TextMuted,
                        modifier = Modifier.padding(bottom = 1.dp)
                    )
                    Row(verticalAlignment = Alignment.Bottom) {
                        Text(
                            text = "Rp${totalPrice / 1000}rb",
                            fontFamily = Syne,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 18.sp,
                            color = Lime,
                            letterSpacing = (-0.02).em
                        )
                        Text(
                            text = "/sesi",
                            fontFamily = DMSans,
                            fontSize = 11.sp,
                            color = TextMuted,
                            modifier = Modifier.padding(bottom = 2.dp)
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(Lime)
                        .clickable { onBookClick() }
                        .padding(horizontal = 20.dp, vertical = 10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Pesan →",
                        fontFamily = Syne,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 12.sp,
                        color = Dark,
                        letterSpacing = (-0.01).em
                    )
                }
            }
        }
    }
}

@Composable
fun InfoChip(icon: String, text: String) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(Surface2)
            .border(1.dp, Border, RoundedCornerShape(10.dp))
            .padding(horizontal = 11.dp, vertical = 7.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = icon, fontSize = 13.sp)
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = text,
            fontFamily = DMSans,
            fontSize = 11.sp,
            color = TextPrimary
        )
    }
}

enum class SlotState {
    AVAILABLE, BOOKED, SELECTED
}

data class SlotData(
    val time: String,
    val state: SlotState
)
