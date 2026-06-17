package com.example.gymmembershipapp.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/** App user account (staff/trainer) used for login. Separate from gym members. */
@Entity(
    tableName = "accounts",
    indices = [Index(value = ["email"], unique = true)]
)
data class AccountEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val email: String,
    val passwordHash: String
)
