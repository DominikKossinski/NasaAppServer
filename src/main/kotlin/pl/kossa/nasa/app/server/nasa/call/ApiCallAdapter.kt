package pl.kossa.nasa.app.server.nasa.call

import com.example.nasa_app.api.call.ApiResponse
import okhttp3.ResponseBody
import pl.kossa.nasa.app.server.nasa.models.ApiErrorBody
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Converter
import java.lang.reflect.Type

class ApiCallAdapter<S: Any>(
    private val successType: Type,
    private val errorBodyConverter: Converter<ResponseBody, ApiErrorBody>
) : CallAdapter<S, Call<ApiResponse<S>>> {

    override fun responseType(): Type = successType

    override fun adapt(call: Call<S>): Call<ApiResponse<S>> {
        return ApiCall(call, errorBodyConverter)
    }
}