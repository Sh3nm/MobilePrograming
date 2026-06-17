package com.example.gymmembershipapp.domain

data class Reward(
    val id: Int,
    val name: String,
    val description: String,
    val pointsRequired: Int,
    val emoji: String
)

// Hardcoded reward catalogue (not stored in the database).
val rewardList = listOf(
    Reward(1, "Botol Minum", "Tumbler FitTrack edisi eksklusif", 50, "🧴"),
    Reward(2, "Protein Shake Gratis", "1x minuman protein di gym bar", 100, "🥤"),
    Reward(3, "Sesi Personal Training Gratis", "1 sesi PT dengan trainer pilihan", 200, "🏋️"),
    Reward(4, "Gym T-Shirt Eksklusif", "Kaos FitTrack limited edition", 300, "👕")
)
