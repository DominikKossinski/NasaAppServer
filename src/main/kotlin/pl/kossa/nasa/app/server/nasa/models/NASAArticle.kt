package pl.kossa.nasa.app.server.nasa.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class NASAArticle(
    val date: Date,
    val explanation: String,
    val hdurl: String,
    @SerializedName("media_type")
    val mediaType: NASAMediaType,
    val title: String,
    val url: String,
    val copyright: String?
)

enum class NASAMediaType {
    @SerializedName("image")
    IMAGE,

    @SerializedName("video")
    VIDEO
}