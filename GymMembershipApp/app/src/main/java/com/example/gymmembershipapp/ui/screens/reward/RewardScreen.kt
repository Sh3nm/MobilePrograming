package com.example.gymmembershipapp.ui.screens.reward

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gymmembershipapp.domain.Reward
import com.example.gymmembershipapp.domain.getMemberLevel
import com.example.gymmembershipapp.domain.rewardList
import com.example.gymmembershipapp.ui.AppViewModelProvider
import com.example.gymmembershipapp.ui.components.LevelBadge
import com.example.gymmembershipapp.ui.components.RewardCard
import com.example.gymmembershipapp.ui.theme.AccentGreen
import com.example.gymmembershipapp.ui.theme.BackgroundDark
import com.example.gymmembershipapp.ui.theme.ErrorRed
import com.example.gymmembershipapp.ui.theme.SurfaceDark
import com.example.gymmembershipapp.ui.theme.SurfaceElevated
import com.example.gymmembershipapp.ui.theme.TextSecondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RewardScreen(
    onBack: () -> Unit,
    viewModel: RewardViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val member by viewModel.member.collectAsStateWithLifecycle()
    val message by viewModel.message.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    var pendingReward by remember { mutableStateOf<Reward?>(null) }

    androidx.compose.runtime.LaunchedEffect(message) {
        message?.let {
            snackbarHostState.showSnackbar(it)
            viewModel.consumeMessage()
        }
    }

    val currentPoints = member?.points ?: 0

    Scaffold(
        containerColor = BackgroundDark,
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text("Reward", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Kembali")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = BackgroundDark,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(vertical = 12.dp)
        ) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = SurfaceDark),
                    border = BorderStroke(1.dp, AccentGreen.copy(alpha = 0.4f))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = null,
                            tint = AccentGreen,
                            modifier = Modifier.size(28.dp)
                        )
                        Spacer(Modifier.width(10.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "POIN KAMU: $currentPoints",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp
                            )
                            Text(text = "Level member", color = TextSecondary, fontSize = 12.sp)
                        }
                        LevelBadge(level = getMemberLevel(currentPoints))
                    }
                }
            }
            item {
                Text(
                    text = "PILIH REWARD",
                    color = TextSecondary,
                    fontSize = 12.sp,
                    letterSpacing = 1.5.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            items(rewardList, key = { it.id }) { reward ->
                RewardCard(
                    reward = reward,
                    currentPoints = currentPoints,
                    onRedeem = { pendingReward = reward }
                )
            }
        }
    }

    pendingReward?.let { reward ->
        val remaining = currentPoints - reward.pointsRequired
        AlertDialog(
            onDismissRequest = { pendingReward = null },
            containerColor = SurfaceElevated,
            title = { Text("Tukar Reward", color = Color.White, fontWeight = FontWeight.Bold) },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text("Kamu akan menukar:", color = TextSecondary)
                    Text("${reward.emoji} ${reward.name}", color = Color.White, fontWeight = FontWeight.SemiBold)
                    Text("dengan ${reward.pointsRequired} poin", color = TextSecondary)
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = "Sisa poin: $currentPoints → $remaining",
                        color = if (remaining >= 0) AccentGreen else ErrorRed,
                        fontWeight = FontWeight.Bold
                    )
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    viewModel.redeem(reward)
                    pendingReward = null
                }) {
                    Text("YA, TUKAR", color = AccentGreen, fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { pendingReward = null }) {
                    Text("Batal", color = TextSecondary)
                }
            }
        )
    }
}
