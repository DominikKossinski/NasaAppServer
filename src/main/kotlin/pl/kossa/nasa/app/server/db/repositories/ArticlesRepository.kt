package pl.kossa.nasa.app.server.db.repositories

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import pl.kossa.nasa.app.server.db.data.Article
import java.util.*

interface ArticlesRepository : CrudRepository<Article, Date> {
    @Query(value="select a from Article a where a.date BETWEEN ?1 AND ?2")
    fun findAllBetween(from: Date, to: Date): List<Article>

}