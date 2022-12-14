package pl.kossa.nasa.app.server.nasa

import com.google.gson.GsonBuilder
import pl.kossa.nasa.app.server.nasa.call.ApiResponseAdapterFactory
import okhttp3.OkHttpClient
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Service
import pl.kossa.nasa.app.server.nasa.api.NASAApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Service("NasaApiModule")
class NasaApiModule {

    @Bean
    fun okHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @Bean
    fun retrofit(client: OkHttpClient): Retrofit {
        val gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd")
            .create()
        return Retrofit.Builder()
            .client(client)
            .baseUrl(System.getenv("NASA_API_URL"))
            .addCallAdapterFactory(ApiResponseAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Bean
    fun nasaApi(retrofit: Retrofit): NASAApi {
        return retrofit.create(NASAApi::class.java)
    }
}