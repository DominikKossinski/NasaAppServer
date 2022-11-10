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