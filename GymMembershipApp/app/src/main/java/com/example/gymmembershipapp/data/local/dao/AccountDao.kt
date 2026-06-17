package com.example.gymmembershipapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gymmembershipapp.data.local.entity.AccountEntity

@Dao
interface AccountDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertAccount(account: AccountEntity): Long

    @Query("SELECT * FROM accounts WHERE email = :email LIMIT 1")
    suspend fun getByEmail(email: String): AccountEntity?

    @Query("SELECT COUNT(*) FROM accounts WHERE email = :email")
    suspend fun countByEmail(email: String): Int
}
