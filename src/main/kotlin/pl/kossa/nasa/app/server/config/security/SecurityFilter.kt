package pl.kossa.nasa.app.server.config.security

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.multipart.MaxUploadSizeExceededException
import pl.kossa.nasa.app.server.data.security.Credentials
import pl.kossa.nasa.app.server.data.security.SecurityUser
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class SecurityFilter : OncePerRequestFilter() {

    @Autowired
    lateinit var securityService: SecurityService


    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        verifyToken(request)
        try {
            filterChain.doFilter(request, response)
        } catch (e: Exception) {
            logger.error("Error $e")
            if (e.cause is MaxUploadSizeExceededException) {
                throw e.cause ?: e
            } else {
                throw e
            }
        }
    }

    @Throws(FirebaseAuthException::class)
    private fun verifyToken(request: HttpServletRequest) {
        val token = securityService.getBearerToken(request)
        if (token != null) {
            val decodedToken = FirebaseAuth.getInstance().verifyIdToken(token)
            logger.info(token)
            decodedToken?.let {
                // TODO  val fireUser = FirebaseAuth.getInstance().getUser(it.uid)
                // TODO val providerId = fireUser.providerData[0].providerId
                // TODO val providerType = ProviderType.getProviderType(providerId)
                val user = SecurityUser(it.uid, it.email, it.isEmailVerified)
                logger.info("DecodedToken: $user")
                // TODO throw when email is not verified
                val authentication = UsernamePasswordAuthenticationToken(user, Credentials(decodedToken, token))
                authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = authentication
            }
        }
    }
}