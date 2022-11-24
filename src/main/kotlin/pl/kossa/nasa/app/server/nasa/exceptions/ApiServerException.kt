package pl.kossa.nasa.app.server.nasa.exceptions

import pl.kossa.nasa.app.server.nasa.models.ApiError
import java.lang.Exception

class ApiServerException(val apiError: ApiError): Exception()