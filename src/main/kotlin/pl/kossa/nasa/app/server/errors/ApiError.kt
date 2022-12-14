package pl.kossa.nasa.app.server.errors

abstract class ApiError {
    abstract val message: String
    abstract val description: String
}

class UnauthorizedError : ApiError() {
    override val message: String
        get() = "Unauthorized"

    override val description: String
        get() = "Unauthorized"
}

class ForbiddenError : ApiError() {
    override val message: String
        get() = "Forbidden"

    override val description: String
        get() = "Forbidden"
}

class NotFoundError(override val description: String) : ApiError() {
    override val message: String
        get() = "NotFound"
}

class UserNotFoundError(override val description: String) : ApiError() {

    override val message: String
        get() = "UserNotFound"
}

class ArticleCommentNotFoundError(override val description: String): ApiError() {
    override val message: String
        get() = "ArticleCommentNotFound"
}

class SavedArticleNotFoundError(override val description: String) : ApiError() {
    override val message: String
        get() = "SavedArticleNotFound"
}

class ArticleAlreadySavedError(override val description: String) : ApiError() {
    override val message: String
        get() = "ArticleAlreadySavedError"
}