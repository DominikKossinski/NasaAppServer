package pl.kossa.nasa.app.server.exceptions

abstract class ApiException(override val message: String?) : Exception()

class UnauthorizedException : ApiException("Unauthorized")

class ForbiddenException : ApiException("Forbidden")

class NotFoundException(message: String?) : ApiException(message)

class UserNotFoundException(userId: String) : ApiException("User with id $userId not found")

class ArticleCommentNotFoundException(commentId: Int) : ApiException("Comment with id $commentId not found")