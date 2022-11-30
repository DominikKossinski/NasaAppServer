package pl.kossa.nasa.app.server.db.data

import javax.persistence.*

@Entity
@Table(name="SavedArticles")
data class SavedArticle(
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id", columnDefinition = "numeric")
    val id: Int,
    @ManyToOne
    val article: Article,
    @ManyToOne
    val user: User
)