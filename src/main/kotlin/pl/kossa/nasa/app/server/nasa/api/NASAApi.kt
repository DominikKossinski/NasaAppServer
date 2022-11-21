package pl.kossa.nasa.app.server.nasa.api

import com.example.nasa_app.api.call.ApiResponse
import pl.kossa.nasa.app.server.nasa.models.NASAArticle
import retrofit2.http.GET
import retrofit2.http.Query

interface NASAApi {

    @GET("planetary/apod")
    suspend fun getArticle(
        @Query("date") date: String,
        @Query("api_key") apiKey: String
    ): ApiResponse<NASAArticle>

    @GET("planetary/apod")
    suspend fun getArticles(
        @Query("api_key") apikey: String,
        @Query("start_date") start_date: String,
        @Query("end_date") endDate: String
    ): ApiResponse<List<NASAArticle>>
}