package pl.kossa.nasa.app.server.extensions

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

fun Date.toApiString(): String {
    val formatter = SimpleDateFormat("YYYY-MM-dd")
    return formatter.format(this)
}

fun LocalDate.toApiString(): String {
    val formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd")
    return formatter.format(this)
}