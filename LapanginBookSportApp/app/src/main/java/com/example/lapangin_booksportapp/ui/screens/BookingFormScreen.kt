package com.example.lapangin_booksportapp.ui.screens

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
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
import androidx.compose.ui.graphics.Color
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
fun BookingFormScreen(
    onBackClick: () -> Unit,
    onSubmitClick: () -> Unit = {}
) {
    var selectedSport by remember { mutableStateOf("Futsal") }
    var noteText by remember { mutableStateOf("") } // For real text field, but we'll use a mocked UI for now to match the HTML design closely
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Dark)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // Top Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 42.dp, start = 18.dp, end = 18.dp, bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Surface2)
                        .border(1.dp, Border, RoundedCornerShape(10.dp))
                        .clickable { onBackClick() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = "Back",
                        tint = TextPrimary,
                        modifier = Modifier.size(20.dp)
                    )
                }
                
                Spacer(modifier = Modifier.width(12.dp))
                
                Text(
                    text = "Form Pemesanan",
                    fontFamily = Syne,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 16.sp,
                    color = TextPrimary,
                    letterSpacing = (-0.02).em
                )
            }
            
            Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(Border))

            // Progress Steps
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp, vertical = 14.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Box(modifier = Modifier.weight(1f).height(3.dp).clip(RoundedCornerShape(100.dp)).background(Lime))
                Box(modifier = Modifier.weight(1f).height(3.dp).clip(RoundedCornerShape(100.dp)).background(Lime)) // Make step 2 active as in mockup
                Box(modifier = Modifier.weight(1f).height(3.dp).clip(RoundedCornerShape(100.dp)).background(Surface3))
                Box(modifier = Modifier.weight(1f).height(3.dp).clip(RoundedCornerShape(100.dp)).background(Surface3))
            }

            // Form Body
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp, vertical = 4.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                
                // JENIS OLAHRAGA
                FormGroup(label = "JENIS OLAHRAGA") {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        SportChip(
                            icon = "⚽",
                            name = "Futsal",
                            isSelected = selectedSport == "Futsal",
                            modifier = Modifier.weight(1f),
                            onClick = { selectedSport = "Futsal" }
                        )
                        SportChip(
                            icon = "🏸",
                            name = "Badminton",
                            isSelected = selectedSport == "Badminton",
                            modifier = Modifier.weight(1f),
                            onClick = { selectedSport = "Badminton" }
                        )
                        SportChip(
                            icon = "🏀",
                            name = "Basket",
                            isSelected = selectedSport == "Basket",
                            modifier = Modifier.weight(1f),
                            onClick = { selectedSport = "Basket" }
                        )
                    }
                }

                // TANGGAL
                FormGroup(label = "TANGGAL") {
                    MockInputField(
                        value = "Sabtu, 26 April 2025",
                        icon = "📅",
                        isFocused = true
                    )
                }

                // WAKTU
                FormGroup(label = "WAKTU") {
                    MockInputField(
                        value = "19:00 – 21:00 (2 jam)",
                        icon = "⏰",
                        isFocused = false
                    )
                }

                // NAMA & NO. HP
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Box(modifier = Modifier.weight(1f)) {
                        FormGroup(label = "NAMA") {
                            MockInputField(value = "Huraira S.", icon = null)
                        }
                    }
                    Box(modifier = Modifier.weight(1f)) {
                        FormGroup(label = "NO. HP") {
                            MockInputField(value = "081234xxx", icon = null)
                        }
                    }
                }

                // CATATAN
                FormGroup(label = "CATATAN (OPSIONAL)") {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(13.dp))
                            .background(Surface2)
                            .border(1.dp, Border, RoundedCornerShape(13.dp))
                            .padding(horizontal = 14.dp, vertical = 11.dp)
                            .height(60.dp)
                    ) {
                        Text(
                            text = "Tambahkan catatan...",
                            fontFamily = DMSans,
                            fontSize = 13.sp,
                            color = Color(0xFF666666) // muted2
                        )
                    }
                }

            }

            Spacer(modifier = Modifier.height(8.dp))

            // Submit Button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Lime)
                    .clickable { onSubmitClick() }
                    .padding(14.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Lanjut ke Pembayaran →",
                    fontFamily = Syne,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 14.sp,
                    color = Dark,
                    letterSpacing = (-0.01).em
                )
            }

            Text(
                text = "Dengan memesan, kamu setuju dengan Syarat & Ketentuan\nBookSport.",
                fontFamily = DMSans,
                fontSize = 10.sp,
                color = TextMuted,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, bottom = 40.dp)
            )
        }
    }
}

@Composable
fun FormGroup(
    label: String,
    content: @Composable () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            fontFamily = DMSans,
            fontWeight = FontWeight.SemiBold,
            fontSize = 10.5.sp,
            color = TextMuted,
            letterSpacing = 0.08.em,
            modifier = Modifier.padding(bottom = 6.dp)
        )
        content()
    }
}

@Composable
fun SportChip(
    icon: String,
    name: String,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(13.dp))
            .background(if (isSelected) Lime.copy(alpha = 0.06f) else Surface2)
            .border(
                width = 1.dp,
                color = if (isSelected) Lime else Border,
                shape = RoundedCornerShape(13.dp)
            )
            .clickable { onClick() }
            .padding(vertical = 10.dp, horizontal = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = icon, fontSize = 20.sp, modifier = Modifier.padding(bottom = 4.dp))
        Text(
            text = name,
            fontFamily = DMSans,
            fontWeight = FontWeight.Medium,
            fontSize = 10.sp,
            color = if (isSelected) Lime else TextMuted
        )
    }
}

@Composable
fun MockInputField(
    value: String,
    icon: String?,
    isFocused: Boolean = false
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(13.dp))
            .background(Surface2)
            .border(
                width = if (isFocused) 1.dp else 1.dp,
                color = if (isFocused) Lime else Border,
                shape = RoundedCornerShape(13.dp)
            )
            .padding(horizontal = 14.dp, vertical = 11.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = value,
            fontFamily = DMSans,
            fontSize = 13.sp,
            color = TextPrimary
        )
        if (icon != null) {
            Text(text = icon, fontSize = 15.sp)
        }
    }
}
