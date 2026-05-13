package com.example.lapangin_booksportapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
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
import com.example.lapangin_booksportapp.ui.theme.TextMuted2
import com.example.lapangin_booksportapp.ui.theme.TextPrimary

@Composable
fun HomeScreen(
    onVenueClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Dark)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 80.dp) // Padding for bottom nav
        ) {
            // Status Bar Area
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 38.dp, start = 20.dp, end = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "09:41",
                    fontFamily = DMSans,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 13.sp,
                    color = TextPrimary
                )
                Text(
                    text = "●●●● ▲ 88%",
                    fontFamily = DMSans,
                    fontWeight = FontWeight.Medium,
                    fontSize = 11.sp,
                    color = TextMuted
                )
            }

            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 18.dp, start = 20.dp, end = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column {
                    Text(
                        text = "Selamat pagi,",
                        fontFamily = DMSans,
                        fontSize = 12.sp,
                        color = TextMuted,
                        modifier = Modifier.padding(bottom = 2.dp)
                    )
                    Text(
                        text = "Huraira Shenmue 👋",
                        fontFamily = Syne,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = TextPrimary,
                        letterSpacing = (-0.02).em
                    )
                }

                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .clip(CircleShape)
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(Lime, Color(0xFF90B800))
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "HS",
                        fontFamily = Syne,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 14.sp,
                        color = Dark
                    )
                }
            }

            // Search Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 20.dp, end = 20.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(Surface2)
                    .border(1.dp, Border, RoundedCornerShape(14.dp))
                    .padding(horizontal = 14.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = TextMuted2,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "Cari lapangan olahraga...",
                    fontFamily = DMSans,
                    fontSize = 12.5.sp,
                    color = TextMuted2
                )
            }

            // Sport Chips
            var selectedChip by remember { mutableStateOf("Semua") }
            val chips = listOf(
                "Semua",
                "⚽ Futsal",
                "🏸 Badminton",
                "🏀 Basket",
                "🎾 Tenis"
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 14.dp)
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                chips.forEach { chip ->
                    val isSelected = selectedChip == chip
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(100.dp))
                            .background(if (isSelected) Lime else Surface2)
                            .border(
                                width = 1.dp,
                                color = if (isSelected) Lime else Border,
                                shape = RoundedCornerShape(100.dp)
                            )
                            .clickable { selectedChip = chip }
                            .padding(horizontal = 14.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = chip,
                            fontFamily = DMSans,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                            fontSize = 11.5.sp,
                            color = if (isSelected) Dark else TextMuted
                        )
                    }
                }
            }

            // Section Title: Populer
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 20.dp, end = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Populer di Sekitar",
                    fontFamily = Syne,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    color = TextPrimary,
                    letterSpacing = (-0.01).em
                )
                Text(
                    text = "Lihat Semua →",
                    fontFamily = DMSans,
                    fontWeight = FontWeight.Medium,
                    fontSize = 11.sp,
                    color = Lime
                )
            }

            // Venue Cards
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                VenueCard(
                    icon = "⚽",
                    badge = "FUTSAL",
                    name = "GOR Manyar",
                    location = "📍 Sukolilo, 1.2 km",
                    price = "80rb",
                    rating = "4.8",
                    bgColors = listOf(Color(0xFF1A2E1A), Color(0xFF0F1F0F)),
                    onClick = onVenueClick
                )
                VenueCard(
                    icon = "🏸",
                    badge = "BADMINTON",
                    name = "PB Mutiara",
                    location = "📍 Gubeng, 2.0 km",
                    price = "50rb",
                    rating = "4.6",
                    bgColors = listOf(Color(0xFF1A1A2E), Color(0xFF0F0F20))
                )
                VenueCard(
                    icon = "🏀",
                    badge = "BASKET",
                    name = "Court Kings",
                    location = "📍 Rungkut, 3.1 km",
                    price = "100rb",
                    rating = "4.9",
                    bgColors = listOf(Color(0xFF2E1A0F), Color(0xFF1F1008))
                )
            }

            // Section Title: Booking Berikutnya
            Text(
                text = "Booking Berikutnya",
                fontFamily = Syne,
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp,
                color = TextPrimary,
                letterSpacing = (-0.01).em,
                modifier = Modifier.padding(top = 16.dp, start = 20.dp, end = 20.dp)
            )

            // Upcoming Booking Card
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 14.dp, start = 20.dp, end = 20.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Surface2)
                    .border(1.dp, Border, RoundedCornerShape(16.dp))
                    .padding(horizontal = 14.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Lime.copy(alpha = 0.1f))
                        .border(1.dp, Lime.copy(alpha = 0.2f), RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "⚽", fontSize = 18.sp)
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "GOR Manyar — Futsal",
                        fontFamily = Syne,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = TextPrimary,
                        modifier = Modifier.padding(bottom = 2.dp),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = "Sabtu, 26 Apr · 19:00 – 21:00",
                        fontFamily = DMSans,
                        fontSize = 10.5.sp,
                        color = TextMuted
                    )
                }

                Box(
                    modifier = Modifier
                        .size(28.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Surface3),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = "Detail",
                        tint = TextMuted,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
        }

        // Bottom Navigation Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .background(Color(0xF2161616)) // 95% opacity surface
                .border(width = 1.dp, color = Border)
                .padding(top = 10.dp, bottom = 20.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomNavItem(
                icon = { Icon(Icons.Default.Home, contentDescription = null, modifier = Modifier.size(20.dp)) },
                label = "Beranda",
                isActive = true
            )
            BottomNavItem(
                icon = { Icon(Icons.Default.List, contentDescription = null, modifier = Modifier.size(20.dp)) },
                label = "Booking",
                isActive = false
            )
            BottomNavItem(
                icon = { Icon(Icons.Default.LocationOn, contentDescription = null, modifier = Modifier.size(20.dp)) },
                label = "Explore",
                isActive = false
            )
            BottomNavItem(
                icon = { Icon(Icons.Default.Person, contentDescription = null, modifier = Modifier.size(20.dp)) },
                label = "Profil",
                isActive = false
            )
        }
    }
}

@Composable
private fun VenueCard(
    icon: String,
    badge: String,
    name: String,
    location: String,
    price: String,
    rating: String,
    bgColors: List<Color>,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .width(155.dp)
            .clip(RoundedCornerShape(18.dp))
            .background(Surface2)
            .border(1.dp, Border, RoundedCornerShape(18.dp))
            .clickable { onClick() }
    ) {
        // Image Area
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
                .background(Brush.linearGradient(colors = bgColors))
        ) {
            Text(
                text = icon,
                fontSize = 32.sp,
                modifier = Modifier.align(Alignment.Center)
            )
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .background(Color.Black.copy(alpha = 0.6f), RoundedCornerShape(100.dp))
                    .border(1.dp, Color.White.copy(alpha = 0.08f), RoundedCornerShape(100.dp))
                    .padding(horizontal = 8.dp, vertical = 2.dp)
            ) {
                Text(
                    text = badge,
                    fontFamily = DMSans,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 9.5.sp,
                    color = Lime,
                    letterSpacing = 0.4.sp
                )
            }
        }

        // Card Body
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Text(
                text = name,
                fontFamily = Syne,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                color = TextPrimary,
                modifier = Modifier.padding(bottom = 3.dp),
                letterSpacing = (-0.01).em
            )
            Text(
                text = location,
                fontFamily = DMSans,
                fontSize = 10.sp,
                color = TextMuted,
                modifier = Modifier.padding(bottom = 7.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.Bottom) {
                    Text(
                        text = price,
                        fontFamily = Syne,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = Lime
                    )
                    Text(
                        text = "/jam",
                        fontFamily = DMSans,
                        fontSize = 9.sp,
                        color = TextMuted,
                        modifier = Modifier.padding(bottom = 1.dp)
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Rating",
                        tint = StarYellow,
                        modifier = Modifier.size(10.dp)
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = rating,
                        fontFamily = DMSans,
                        fontSize = 10.sp,
                        color = TextMuted
                    )
                }
            }
        }
    }
}

@Composable
private fun BottomNavItem(
    icon: @Composable () -> Unit,
    label: String,
    isActive: Boolean
) {
    val color = if (isActive) Lime else TextMuted
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        androidx.compose.material3.LocalContentColor.provides(color)
        Box(contentAlignment = Alignment.Center) {
            icon()
        }
        Text(
            text = label,
            fontFamily = DMSans,
            fontSize = 9.5.sp,
            color = color
        )
        if (isActive) {
            Box(
                modifier = Modifier
                    .size(4.dp)
                    .clip(CircleShape)
                    .background(Lime)
            )
        } else {
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}
