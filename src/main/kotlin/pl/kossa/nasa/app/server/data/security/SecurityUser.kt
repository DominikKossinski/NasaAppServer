package pl.kossa.nasa.app.server.data.security

data class SecurityUser(
    val uid: String,
    val email: String,
    val isEmailVerified: Boolean
)