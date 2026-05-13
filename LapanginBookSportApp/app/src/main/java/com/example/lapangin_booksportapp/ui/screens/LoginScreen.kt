package com.example.lapangin_booksportapp.ui.screens

import android.widget.Toast
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lapangin_booksportapp.ui.theme.Border
import com.example.lapangin_booksportapp.ui.theme.Dark
import com.example.lapangin_booksportapp.ui.theme.Lime
import com.example.lapangin_booksportapp.ui.theme.LimeSubtle
import com.example.lapangin_booksportapp.ui.theme.Surface2
import com.example.lapangin_booksportapp.ui.theme.Syne
import com.example.lapangin_booksportapp.ui.theme.DMSans
import com.example.lapangin_booksportapp.ui.theme.TextMuted
import com.example.lapangin_booksportapp.ui.theme.TextMuted2
import com.example.lapangin_booksportapp.ui.theme.TextPrimary

@Composable
fun LoginScreen(
    onBackClick: () -> Unit,
    onLoginSuccess: () -> Unit,
    onRegisterClick: () -> Unit
) {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Dark)
    ) {
        // Background decorative sport emojis (faded)
        Text(
            text = "⚽",
            fontSize = 80.sp,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .offset(x = 20.dp, y = 100.dp)
                .alpha(0.05f)
        )
        Text(
            text = "🏸",
            fontSize = 60.sp,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .offset(x = (-10).dp, y = 80.dp)
                .alpha(0.04f)
        )
        Text(
            text = "🏀",
            fontSize = 70.sp,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(x = 15.dp, y = (-100).dp)
                .alpha(0.04f)
        )

        // Radial glow behind header
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = 80.dp)
                .size(260.dp)
                .alpha(0.2f)
                .blur(100.dp)
                .background(
                    color = LimeSubtle,
                    shape = CircleShape
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
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
                    text = "Masuk",
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

            // Content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 18.dp, vertical = 20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Welcome header
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "👋",
                        fontSize = 40.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                SpanStyle(
                                    color = TextPrimary,
                                    fontFamily = Syne,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 26.sp,
                                )
                            ) {
                                append("Selamat ")
                            }
                            withStyle(
                                SpanStyle(
                                    color = Lime,
                                    fontFamily = Syne,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 26.sp,
                                )
                            ) {
                                append("Datang!")
                            }
                        },
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "Masuk ke akunmu untuk booking lapangan",
                        fontFamily = DMSans,
                        fontWeight = FontWeight.Normal,
                        fontSize = 13.sp,
                        color = TextMuted,
                        textAlign = TextAlign.Center
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Email field
                LoginTextField(
                    label = "EMAIL",
                    value = email,
                    onValueChange = { email = it },
                    placeholder = "contoh@email.com",
                    keyboardType = KeyboardType.Email
                )

                // Password field
                LoginTextField(
                    label = "PASSWORD",
                    value = password,
                    onValueChange = { password = it },
                    placeholder = "Masukkan password",
                    keyboardType = KeyboardType.Password,
                    isPassword = true,
                    passwordVisible = passwordVisible,
                    onTogglePasswordVisibility = { passwordVisible = !passwordVisible }
                )

                // Forgot password
                Text(
                    text = "Lupa password?",
                    fontFamily = DMSans,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp,
                    color = Lime,
                    modifier = Modifier
                        .align(Alignment.End)
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) { /* TODO: Forgot password */ }
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Login button
                Button(
                    onClick = {
                        if (email == "admin@gmail.com" && password == "123") {
                            onLoginSuccess()
                        } else {
                            Toast.makeText(context, "Email atau password salah!", Toast.LENGTH_SHORT).show()
                        }
                    },
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
                        text = "Masuk",
                        fontFamily = Syne,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 15.sp,
                        letterSpacing = (-0.01).sp
                    )
                }

                // Terms note
                Text(
                    text = "Dengan masuk, kamu setuju dengan Syarat & Ketentuan BookSport.",
                    fontFamily = DMSans,
                    fontWeight = FontWeight.Normal,
                    fontSize = 10.sp,
                    color = TextMuted2,
                    lineHeight = 16.sp,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Register link
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Belum punya akun? ",
                        fontFamily = DMSans,
                        fontWeight = FontWeight.Normal,
                        fontSize = 13.sp,
                        color = TextMuted2
                    )
                    Text(
                        text = "Daftar",
                        fontFamily = DMSans,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = Lime,
                        modifier = Modifier.clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) { onRegisterClick() }
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
private fun LoginTextField(
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
