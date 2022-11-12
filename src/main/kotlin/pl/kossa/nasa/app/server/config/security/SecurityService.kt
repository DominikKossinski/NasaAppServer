package pl.kossa.nasa.app.server.config.security

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils
import pl.kossa.nasa.app.server.data.security.SecurityUser
import javax.servlet.http.HttpServletRequest

@Service
class SecurityService {

    fun getBearerToken(request: HttpServletRequest): String? {
        val authorization = request.getHeader("Authorization")
        if (StringUtils.hasText(authorization) && authorization.startsWith("Bearer ")) {
            return authorization.substring(7, authorization.length)
        }
        return null
    }

    fun getUser(): SecurityUser? {
        return SecurityContextHolder.getContext().authentication.principal as? SecurityUser
    }
}