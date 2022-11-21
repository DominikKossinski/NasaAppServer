package pl.kossa.nasa.app.server.nasa.models

import java.lang.Exception

data class ApiErrorBody(
    val message: String
)

data class ApiError(
    val code: Int,
    val body: ApiErrorBody?,
    val exception: Exception? = null
)
