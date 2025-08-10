package com.example

import com.example.APIInterface.APIRequest
import com.example.APIInterface.APIResponse
import com.example.user.ValidateWT
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respondText
import org.koin.core.context.GlobalContext
import org.koin.core.parameter.parametersOf
import java.util.UUID

inline fun <reified T> ApplicationCall.inject(): T = GlobalContext.get().get()

inline fun <reified T> ApplicationCall.inject(vararg params: Any?): T =
    GlobalContext.get().get { parametersOf(*params) }

suspend fun ApplicationCall.requireWebToken(): UUID {
    val wt = request.cookies["WebToken"] ?: request.headers["WebToken"]
    if (wt == null) {
        response.status(value = HttpStatusCode.Unauthorized)
        respondText(text = "Headers do not contain web token.")
        throw IllegalStateException("Missing WebToken")
    }
    return UUID.fromString(wt)
}

suspend fun ApplicationCall.validateAndGetUser(): ValidateWT.Response {
    val validateWT = GlobalContext.get().get<ValidateWT>()
    val wt = requireWebToken()
    return try {
        validateWT.execute(ValidateWT.Request(wt = wt))
    } catch (exception: ValidateWT.ValidateWTException) {
        when (exception) {
            is ValidateWT.ValidateWTException.InvalidWTException -> {
                response.status(value = HttpStatusCode.Unauthorized)
                respondText(text = "Invalid or expired web token.")
                throw IllegalStateException("Unauthorized: invalid WT", exception)
            }
        }
    }
}

suspend inline fun <reified T : APIInterface<Req, Res>, Req : APIRequest, Res : APIResponse>
    ApplicationCall.executeAuthenticated(request: Req): Res {
    val (userId, _) = validateAndGetUser()
    val api = inject<T>(userId)
    return api.execute(request)
}
