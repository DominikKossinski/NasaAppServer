package pl.kossa.nasa.app.server.exceptions

import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import pl.kossa.nasa.app.server.errors.*


@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
class RestExceptionsHandler {

    @ExceptionHandler(UnauthorizedException::class)
    fun handleUnauthorizedException(
        unauthorizedException: UnauthorizedException
    ): ResponseEntity<UnauthorizedError> {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value()).body(UnauthorizedError())
    }

    @ExceptionHandler(ForbiddenException::class)
    fun handleForbiddenException(
        forbiddenException: ForbiddenException
    ): ResponseEntity<ForbiddenError> {
        return ResponseEntity.status(HttpStatus.FORBIDDEN.value()).body(ForbiddenError())
    }

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(
        notFoundException: NotFoundException
    ): ResponseEntity<NotFoundError> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(NotFoundError(notFoundException.message ?: ""))
    }

    @ExceptionHandler(UserNotFoundException::class)
    fun handleUserNotFoundException(
        userNotFoundException: UserNotFoundException
    ): ResponseEntity<UserNotFoundError> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value())
            .body(UserNotFoundError(userNotFoundException.message ?: ""))
    }

    @ExceptionHandler(ArticleCommentNotFoundException::class)
    fun handleArticleCommentNotFoundException(
        articleCommentNotFoundException: ArticleCommentNotFoundException
    ): ResponseEntity<ArticleCommentNotFoundError> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value())
            .body(ArticleCommentNotFoundError(articleCommentNotFoundException.message ?: ""))
    }

    @ExceptionHandler(ArticleAlreadySavedException::class)
    fun handleArticleAlreadySavedException(
        articleAlreadySavedException: ArticleAlreadySavedException
    ): ResponseEntity<ArticleAlreadySavedError> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
            .body(ArticleAlreadySavedError(articleAlreadySavedException.message ?: ""))
    }

    @ExceptionHandler(SavedArticleNotFoundException::class)
    fun handleSavedArticleNotFoundException(
        savedArticleNotFoundException: SavedArticleNotFoundException
    ): ResponseEntity<SavedArticleNotFoundError> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value())
            .body(SavedArticleNotFoundError(savedArticleNotFoundException.message ?: ""))
    }
}