package com.example.gymmembershipapp.domain

import androidx.compose.ui.graphics.Color
import com.example.gymmembershipapp.ui.theme.BronzeColor
import com.example.gymmembershipapp.ui.theme.GoldColor
import com.example.gymmembershipapp.ui.theme.SilverColor

enum class MemberLevel(val label: String, val color: Color, val emoji: String) {
    BRONZE("Bronze", BronzeColor, "🥉"),  // 🥉
    SILVER("Silver", SilverColor, "🥈"),  // 🥈
    GOLD("Gold", GoldColor, "🥇")          // 🥇
}

/**
 * Level rules:
 *  Bronze = 0–99, Silver = 100–249, Gold = 250+
 */
fun getMemberLevel(points: Int): MemberLevel = when {
    points >= 250 -> MemberLevel.GOLD
    points >= 100 -> MemberLevel.SILVER
    else -> MemberLevel.BRONZE
}

/** 1 point per 5 minutes of workout. */
fun calculatePoints(durationMinutes: Int): Int = durationMinutes / 5
