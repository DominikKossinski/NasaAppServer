package pl.kossa.nasa.app.server.architecture

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import pl.kossa.nasa.app.server.config.security.SecurityService
import pl.kossa.nasa.app.server.db.data.User
import pl.kossa.nasa.app.server.errors.UnauthorizedError
import pl.kossa.nasa.app.server.exceptions.UnauthorizedException
import pl.kossa.nasa.app.server.services.UserService

abstract class BaseRestController {

    protected val logger = LoggerFactory.getLogger(this::class.java)

    @Autowired
    protected lateinit var securityService: SecurityService

    @Autowired
    protected lateinit var userService: UserService

    protected suspend fun getUserDetails(): User {
        val user = securityService.getUser() ?: throw UnauthorizedException()
        val dbUser = userService.getUserById(user.uid) ?: return userService.save(User(user.uid, user.email))
        return dbUser
    }
}