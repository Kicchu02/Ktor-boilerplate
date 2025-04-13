package com.example.user

import com.example.DatabaseFactory
import com.example.queries.CheckIfUserExistsByEmail
import com.example.queries.InsertIntoUser
import com.example.user.SignUp.SignUpException.EmailAlreadyExistsException
import org.jooq.DSLContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.security.MessageDigest
import java.security.SecureRandom
import java.util.Base64
import java.util.UUID

internal class SignUpServerImpl : SignUp(), KoinComponent {
    private val insertIntoUser by inject<InsertIntoUser>()
    private val checkIfUserExistsByEmail by inject<CheckIfUserExistsByEmail>()

    override suspend fun execute(request: Request): Response {
        return DatabaseFactory.transaction { ctx ->
            validateRequest(ctx = ctx, request = request)
            val userId = UUID.randomUUID()
            val salt = generateSalt()
            val hashedPassword = hashPassword(request.password, salt)
            insertIntoUser.execute(
                ctx = ctx,
                input = InsertIntoUser.Input(
                    id = userId,
                    email = request.emailId,
                    password = hashedPassword,
                    salt = salt,
                ),
            )
            Response(userId = userId)
        }
    }

    private fun validateRequest(ctx: DSLContext, request: Request) {
        val isEmailExisting = checkIfUserExistsByEmail.execute(
            ctx = ctx,
            input = CheckIfUserExistsByEmail.Input(emailId = request.emailId),
        ).exists
        if (isEmailExisting) {
            throw EmailAlreadyExistsException("Email id: ${request.emailId} already exists in the system.")
        }
    }

    private fun generateSalt(): String {
        val saltBytes = ByteArray(16)
        SecureRandom().nextBytes(saltBytes)
        return Base64.getEncoder().encodeToString(saltBytes)
    }

    private fun hashPassword(password: String, salt: String): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val combined = (password + salt).toByteArray()
        val hashedBytes = digest.digest(combined)
        return Base64.getEncoder().encodeToString(hashedBytes)
    }
}
