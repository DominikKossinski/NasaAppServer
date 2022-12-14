package pl.kossa.nasa.app.server.db.data

import com.fasterxml.jackson.annotation.JsonFormat
import pl.kossa.nasa.app.server.nasa.models.NASAArticle
import pl.kossa.nasa.app.server.nasa.models.NASAMediaType
import java.time.LocalDate
import java.time.ZoneId
import javax.persistence.*


@Entity
@Table(name = "Articles")
data class Article(
    @Id
    @Basic
    @JsonFormat(pattern="yyyy-MM-dd")
    val date: LocalDate,
    @Column(name = "explanation", columnDefinition = "varchar(2000)")
    val explanation: String,
    @Column(name = "hdurl", columnDefinition = "varchar(500)", nullable = true)
    val hdurl: String?,
    @Column(name = "mediaType", columnDefinition = "varchar(100)")
    val mediaType: NasaMediaType,
    @Column(name = "title", columnDefinition = "varchar(500)")
    val title: String,
    @Column(name = "url", columnDefinition = "varchar(500)")
    val url: String,
    @Column(name = "copyright", columnDefinition = "varchar(500)", nullable = true)
    val copyright: String?
) {

    companion object {
        fun fromNASAArticle(nasaArticle: NASAArticle): Article {
            return Article(
                nasaArticle.date.toInstant().atZone(ZoneId.of("CET")).toLocalDate(),
                nasaArticle.explanation,
                nasaArticle.hdurl,
                NasaMediaType.fromNASAMediaType(nasaArticle.mediaType),
                nasaArticle.title,
                nasaArticle.url,
                nasaArticle.copyright
            )
        }
    }
}


enum class NasaMediaType {
    IMAGE,
    VIDEO;

    companion object {
        fun fromNASAMediaType(nasaMediaType: NASAMediaType): NasaMediaType {
            return when (nasaMediaType) {
                NASAMediaType.IMAGE -> IMAGE
                NASAMediaType.VIDEO -> VIDEO
            }
        }
    }
}
