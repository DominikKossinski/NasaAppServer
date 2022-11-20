package pl.kossa.nasa.app.server.db.repositories

import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import pl.kossa.nasa.app.server.db.data.Article
import java.util.*

interface ArticlesRepository : PagingAndSortingRepository<Article, Date> {

}