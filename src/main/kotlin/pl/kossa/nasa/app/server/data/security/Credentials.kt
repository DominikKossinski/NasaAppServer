package pl.kossa.nasa.app.server.data.security

import com.google.firebase.auth.FirebaseToken

data class Credentials(
    val decodedToken: FirebaseToken,
    val tokenId: String
)