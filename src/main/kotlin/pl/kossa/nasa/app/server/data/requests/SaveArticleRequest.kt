package pl.kossa.nasa.app.server.data.requests

import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate
import java.util.Date

data class SaveArticleRequest(
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    val date: LocalDate
)