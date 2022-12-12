package pl.kossa.nasa.app.server.exceptions

import java.util.*

abstract class ApiException(override val message: String?) : Exception()

class UnauthorizedException : ApiException("Unauthorized")

class ForbiddenException : ApiException("Forbidden")

class NotFoundException(message: String?): ApiException(message)

class ArticleAlreadySavedException(date: Date): ApiException("Article from $date already saved")