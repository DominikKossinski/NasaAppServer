package pl.kossa.nasa.app.server.db.data

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table


@Entity
@Table(name = "Articles")
data class Article(
    @Id
    @Column(name="id", columnDefinition = "timestamp")
    val date: Date,
    @Column(name="explanation", columnDefinition = "varchar(2000)")
    val explanation: String,
    @Column(name="hdurl", columnDefinition = "varchar(500)")
    val hdurl: String,
    @Column(name="mediaType", columnDefinition = "varchar(100)")
    val mediaType: NasaMediaType,
    @Column(name="title", columnDefinition = "varchar(500)")
    val title: String,
    @Column(name="url", columnDefinition = "varchar(500)")
    val url: String,
    @Column(name="copyright", columnDefinition = "varchar(500)")
    val copyright: String?
)

enum class NasaMediaType {
    IMAGE,
    VIDEO
}