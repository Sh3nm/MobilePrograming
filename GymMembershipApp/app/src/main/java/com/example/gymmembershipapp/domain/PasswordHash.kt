package com.example.gymmembershipapp.domain

import java.security.MessageDigest

/** Simple SHA-256 hash so passwords are not stored in plain text. */
fun hashPassword(password: String): String {
    val digest = MessageDigest.getInstance("SHA-256")
    return digest.digest(password.toByteArray(Charsets.UTF_8))
        .joinToString("") { "%02x".format(it) }
}
