package pl.kossa.nasa.app.server.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Date.toApiString(): String {
    val formatter = SimpleDateFormat("YYYY-MM-dd")
    return formatter.format(this)
}