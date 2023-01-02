package pl.kossa.nasa.app.server.db.repositories

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import pl.kossa.nasa.app.server.db.data.Article
import java.time.LocalDate

interface ArticlesRepository : CrudRepository<Article, LocalDate> {
    @Query(value="select a from Article a where ?1 <= a.date AND a.date <= ?2 ORDER BY a.date DESC")
    fun findAllBetween(from: LocalDate, to: LocalDate): List<Article>

}