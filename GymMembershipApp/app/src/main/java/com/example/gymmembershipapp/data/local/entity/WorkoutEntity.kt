package com.example.gymmembershipapp.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "workouts",
    foreignKeys = [
        ForeignKey(
            entity = MemberEntity::class,
            parentColumns = ["id"],
            childColumns = ["memberId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("memberId")]
)
data class WorkoutEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val memberId: Int,          // FK -> members.id
    val workoutType: String,    // Cardio | Strength | Yoga | HIIT | Pilates | Cycling
    val duration: Int,          // minutes
    val pointEarned: Int,       // = duration / 5
    val date: String            // format: "dd MMM yyyy, HH:mm"
)
