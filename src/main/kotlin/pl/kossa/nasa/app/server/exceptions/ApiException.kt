package pl.kossa.nasa.app.server.exceptions

abstract class ApiException(override val message: String?) : Exception()

class UnauthorizedException : ApiException("Unauthorized")

class ForbiddenException : ApiException("Forbidden")

class NotFoundException(message: String?): ApiException(message)