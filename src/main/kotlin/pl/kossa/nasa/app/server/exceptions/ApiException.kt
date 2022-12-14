package pl.kossa.nasa.app.server.exceptions

import pl.kossa.nasa.app.server.extensions.toApiString
import java.time.LocalDate
import java.util.*

abstract class ApiException(override val message: String?) : Exception()

class UnauthorizedException : ApiException("Unauthorized")

class ForbiddenException : ApiException("Forbidden")

class NotFoundException(message: String?) : ApiException(message)

class SavedArticleNotFoundException(date: LocalDate) : ApiException("SavedArticle from ${date.toApiString()} not found")
class ArticleAlreadySavedException(date: LocalDate) : ApiException("Article from ${date.toApiString()} already saved")