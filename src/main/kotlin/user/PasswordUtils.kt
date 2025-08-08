package com.example.user

import java.security.MessageDigest
import java.security.SecureRandom
import java.util.Base64

class PasswordUtils {
    internal fun generateSalt(): String {
        val saltBytes = ByteArray(16)
        SecureRandom().nextBytes(saltBytes)
        return Base64.getEncoder().encodeToString(saltBytes)
    }

    internal fun hashPassword(password: String, salt: String): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val combined = (password + salt).toByteArray()
        val hashedBytes = digest.digest(combined)
        return Base64.getEncoder().encodeToString(hashedBytes)
    }

    internal fun isPasswordValid(
        plainPasswordToCheck: String,
        hashedPassword: String,
        salt: String,
    ): Boolean {
        val attemptedHash = hashPassword(password = plainPasswordToCheck, salt = salt)
        return attemptedHash == hashedPassword
    }
}
