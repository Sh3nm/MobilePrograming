package com.example.gymmembershipapp.domain

enum class WorkoutType(val label: String, val emoji: String) {
    CARDIO("Cardio", "🏃"),
    STRENGTH("Strength", "💪"),
    YOGA("Yoga", "🧘"),
    HIIT("HIIT", "⚡"),
    PILATES("Pilates", "🤸"),
    CYCLING("Cycling", "🚴");

    companion object {
        fun fromLabel(label: String): WorkoutType =
            entries.firstOrNull { it.label == label } ?: CARDIO
    }
}
