package com.example.lapangin_booksportapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lapangin_booksportapp.ui.theme.Border
import com.example.lapangin_booksportapp.ui.theme.Dark
import com.example.lapangin_booksportapp.ui.theme.Lime
import com.example.lapangin_booksportapp.ui.theme.LimeBorder
import com.example.lapangin_booksportapp.ui.theme.LimeSubtle
import com.example.lapangin_booksportapp.ui.theme.Surface2
import com.example.lapangin_booksportapp.ui.theme.Surface3
import com.example.lapangin_booksportapp.ui.theme.Syne
import com.example.lapangin_booksportapp.ui.theme.DMSans
import com.example.lapangin_booksportapp.ui.theme.TextMuted
import com.example.lapangin_booksportapp.ui.theme.TextMuted2
import com.example.lapangin_booksportapp.ui.theme.TextPrimary

@Composable
fun RegisterScreen(
    onBackClick: () -> Unit,
    onRegisterSuccess: () -> Unit,
    onLoginClick: () -> Unit
) {
    var namaLengkap by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var noHp by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var konfirmasiPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var konfirmasiPasswordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Dark)
    ) {
        // Top Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 18.dp, end = 18.dp, top = 48.dp, bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Back button
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Surface2)
                    .border(1.dp, Border, RoundedCornerShape(10.dp))
                    .clickable { onBackClick() },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "‹",
                    fontSize = 20.sp,
                    color = TextPrimary,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Text(
                text = "Daftar Akun",
                fontFamily = Syne,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 18.sp,
                color = TextPrimary,
                letterSpacing = (-0.02).sp
            )
        }

        // Divider
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Border)
        )

        // Form content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 18.dp, vertical = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Subtitle
            Text(
                text = "Buat akun baru untuk mulai booking lapangan olahraga favoritmu.",
                fontFamily = DMSans,
                fontWeight = FontWeight.Normal,
                fontSize = 13.sp,
                color = TextMuted,
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Nama Lengkap
            BookSportTextField(
                label = "NAMA LENGKAP",
                value = namaLengkap,
                onValueChange = { namaLengkap = it },
                placeholder = "Masukkan nama lengkap",
                keyboardType = KeyboardType.Text
            )

            // Email
            BookSportTextField(
                label = "EMAIL",
                value = email,
                onValueChange = { email = it },
                placeholder = "contoh@email.com",
                keyboardType = KeyboardType.Email
            )

            // No. HP
            BookSportTextField(
                label = "NO. HP",
                value = noHp,
                onValueChange = { noHp = it },
                placeholder = "08xxxxxxxxxx",
                keyboardType = KeyboardType.Phone
            )

            // Password
            BookSportTextField(
                label = "PASSWORD",
                value = password,
                onValueChange = { password = it },
                placeholder = "Minimal 8 karakter",
                keyboardType = KeyboardType.Password,
                isPassword = true,
                passwordVisible = passwordVisible,
                onTogglePasswordVisibility = { passwordVisible = !passwordVisible }
            )

            // Konfirmasi Password
            BookSportTextField(
                label = "KONFIRMASI PASSWORD",
                value = konfirmasiPassword,
                onValueChange = { konfirmasiPassword = it },
                placeholder = "Ulangi password",
                keyboardType = KeyboardType.Password,
                isPassword = true,
                passwordVisible = konfirmasiPasswordVisible,
                onTogglePasswordVisibility = { konfirmasiPasswordVisible = !konfirmasiPasswordVisible }
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Register button
            Button(
                onClick = onRegisterSuccess,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Lime,
                    contentColor = Dark
                ),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 0.dp
                )
            ) {
                Text(
                    text = "Daftar",
                    fontFamily = Syne,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 15.sp,
                    letterSpacing = (-0.01).sp
                )
            }

            // Terms note
            Text(
                text = "Dengan mendaftar, kamu setuju dengan Syarat & Ketentuan BookSport.",
                fontFamily = DMSans,
                fontWeight = FontWeight.Normal,
                fontSize = 10.sp,
                color = TextMuted2,
                lineHeight = 16.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Login link
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Sudah punya akun? ",
                    fontFamily = DMSans,
                    fontWeight = FontWeight.Normal,
                    fontSize = 13.sp,
                    color = TextMuted2
                )
                Text(
                    text = "Masuk",
                    fontFamily = DMSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    color = Lime,
                    modifier = Modifier.clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) { onLoginClick() }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun BookSportTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    isPassword: Boolean = false,
    passwordVisible: Boolean = false,
    onTogglePasswordVisibility: (() -> Unit)? = null
) {
    var isFocused by remember { mutableStateOf(false) }

    Column {
        // Label
        Text(
            text = label,
            fontFamily = DMSans,
            fontWeight = FontWeight.SemiBold,
            fontSize = 10.5.sp,
            color = TextMuted,
            letterSpacing = 0.8.sp,
            modifier = Modifier.padding(bottom = 6.dp)
        )

        // Input field
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(13.dp))
                .background(Surface2)
                .then(
                    if (isFocused) {
                        Modifier.border(1.dp, Lime, RoundedCornerShape(13.dp))
                    } else {
                        Modifier.border(1.dp, Border, RoundedCornerShape(13.dp))
                    }
                )
                .padding(horizontal = 14.dp, vertical = 13.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BasicTextField(
                    value = value,
                    onValueChange = onValueChange,
                    modifier = Modifier
                        .weight(1f)
                        .onFocusChanged { isFocused = it.isFocused },
                    textStyle = androidx.compose.ui.text.TextStyle(
                        fontFamily = DMSans,
                        fontWeight = FontWeight.Normal,
                        fontSize = 13.sp,
                        color = TextPrimary
                    ),
                    singleLine = true,
                    cursorBrush = SolidColor(Lime),
                    visualTransformation = if (isPassword && !passwordVisible)
                        PasswordVisualTransformation()
                    else
                        VisualTransformation.None,
                    keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
                    decorationBox = { innerTextField ->
                        Box {
                            if (value.isEmpty()) {
                                Text(
                                    text = placeholder,
                                    fontFamily = DMSans,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 13.sp,
                                    color = TextMuted2
                                )
                            }
                            innerTextField()
                        }
                    }
                )

                if (isPassword && onTogglePasswordVisibility != null) {
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = if (passwordVisible) "🙈" else "👁️",
                        fontSize = 16.sp,
                        modifier = Modifier.clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) { onTogglePasswordVisibility() }
                    )
                }
            }
        }
    }
}
