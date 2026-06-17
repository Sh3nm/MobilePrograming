package com.example.gymmembershipapp.data.session

import android.content.Context

/** Persists the logged-in account in SharedPreferences so the session survives app restarts. */
class SessionManager(context: Context) {

    private val prefs =
        context.applicationContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun saveSession(accountId: Int, name: String, email: String) {
        prefs.edit()
            .putInt(KEY_ID, accountId)
            .putString(KEY_NAME, name)
            .putString(KEY_EMAIL, email)
            .apply()
    }

    fun isLoggedIn(): Boolean = prefs.getInt(KEY_ID, -1) != -1

    val name: String get() = prefs.getString(KEY_NAME, "") ?: ""
    val email: String get() = prefs.getString(KEY_EMAIL, "") ?: ""

    fun logout() = prefs.edit().clear().apply()

    companion object {
        private const val PREFS_NAME = "fittrack_session"
        private const val KEY_ID = "account_id"
        private const val KEY_NAME = "account_name"
        private const val KEY_EMAIL = "account_email"
    }
}
