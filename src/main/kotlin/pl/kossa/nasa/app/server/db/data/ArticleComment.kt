package pl.kossa.nasa.app.server.db.data

import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.Date
import javax.persistence.*

@Entity
@Table(name = "ArticleComment")
data class ArticleComment(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "numeric")
    val id: Int,
    @Column(name = "comment", columnDefinition = "varchar(2000)")
    val comment: String,
    @Column(name = "createdAt", columnDefinition = "timestamp")
    val createdAt: Date,
    @Column(name = "updatedAt", columnDefinition = "timestamp")
    val updatedAt: Date?,
    @ManyToOne
    @JsonIgnore
    val article: Article,
    @ManyToOne
    val author: User
) {
    val isEdited = updatedAt != null
}