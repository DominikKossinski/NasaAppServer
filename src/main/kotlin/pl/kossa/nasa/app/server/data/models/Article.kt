package pl.kossa.nasa.app.server.data.models

import java.util.*

data class Article(
    val date: Date,
    val explanation: String,
    val hdurl: String,
    val mediaType: NasaMediaType,
    val title: String,
    val url: String,
    val copyright: String?
)

enum class NasaMediaType {
    IMAGE,
    VIDEO
}