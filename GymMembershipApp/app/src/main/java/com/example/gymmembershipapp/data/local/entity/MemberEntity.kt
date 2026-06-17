package com.example.gymmembershipapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "members")
data class MemberEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val email: String,
    val phone: String,
    val joinDate: String,   // format: "dd MMM yyyy"
    val points: Int = 0,
    val memberId: String    // format: "FT-XXXXXXX"
)
