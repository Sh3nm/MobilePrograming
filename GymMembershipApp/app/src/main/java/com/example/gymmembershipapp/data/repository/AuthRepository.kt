package com.example.gymmembershipapp.data.repository

import com.example.gymmembershipapp.data.local.dao.AccountDao
import com.example.gymmembershipapp.data.local.entity.AccountEntity
import com.example.gymmembershipapp.domain.hashPassword

class AuthRepository(private val accountDao: AccountDao) {

    suspend fun isEmailTaken(email: String): Boolean =
        accountDao.countByEmail(email.trim().lowercase()) > 0

    /** Creates a new account. Returns the created account or null if email exists. */
    suspend fun register(name: String, email: String, password: String): AccountEntity? {
        val normalizedEmail = email.trim().lowercase()
        if (isEmailTaken(normalizedEmail)) return null
        val account = AccountEntity(
            name = name.trim(),
            email = normalizedEmail,
            passwordHash = hashPassword(password)
        )
        val id = accountDao.insertAccount(account)
        return account.copy(id = id.toInt())
    }

    /** Returns the matching account when credentials are valid, otherwise null. */
    suspend fun login(email: String, password: String): AccountEntity? {
        val account = accountDao.getByEmail(email.trim().lowercase()) ?: return null
        return if (account.passwordHash == hashPassword(password)) account else null
    }
}
