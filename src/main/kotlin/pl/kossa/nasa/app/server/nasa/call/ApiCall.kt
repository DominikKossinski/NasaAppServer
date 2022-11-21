package pl.kossa.nasa.app.server.nasa.call

import com.example.nasa_app.api.call.ApiResponse
import com.google.api.client.http.HttpStatusCodes
import okhttp3.Request
import okhttp3.ResponseBody
import okio.Timeout
import pl.kossa.nasa.app.server.exceptions.UnauthorizedException
import pl.kossa.nasa.app.server.nasa.exceptions.ApiServerException
import pl.kossa.nasa.app.server.nasa.models.ApiError
import pl.kossa.nasa.app.server.nasa.models.ApiErrorBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.Response
import java.io.IOException
import java.lang.UnsupportedOperationException
import java.net.http.HttpResponse

class ApiCall<S : Any>(
    private val delegate: Call<S>,
    private val errorConverter: Converter<ResponseBody, ApiErrorBody>
) : Call<ApiResponse<S>> {

    override fun enqueue(callback: Callback<ApiResponse<S>>) {
        return delegate.enqueue(object : Callback<S> {
            override fun onResponse(call: Call<S>, response: Response<S>) {
                if (response.isSuccessful) {
                    callback.onResponse(
                        this@ApiCall,
                        Response.success(ApiResponse(response.body()))
                    )
                } else {
                    if (response.code() == HttpStatusCodes.STATUS_CODE_UNAUTHORIZED) {
                        callback.onFailure(this@ApiCall, UnauthorizedException())
                    } else {
                        val apiError = try {
                            val apiErrorBody = response.errorBody()?.let { errorConverter.convert(it) }
                            ApiError(response.code(), apiErrorBody)
                        } catch (e: IOException) {
                            ApiError(500, null)
                        }
                        callback.onFailure(this@ApiCall, ApiServerException(apiError))
                    }
                }
            }

            override fun onFailure(call: Call<S>, t: Throwable) {
                callback.onFailure(this@ApiCall, t)
            }

        })
    }


    override fun isExecuted() = delegate.isExecuted

    override fun clone() = ApiCall(delegate.clone(), errorConverter)

    override fun isCanceled() = delegate.isCanceled

    override fun cancel() = delegate.cancel()

    override fun execute(): Response<ApiResponse<S>> {
        throw UnsupportedOperationException("Api does not support execute")
    }

    override fun request(): Request = delegate.request()
    override fun timeout(): Timeout = Timeout()
}