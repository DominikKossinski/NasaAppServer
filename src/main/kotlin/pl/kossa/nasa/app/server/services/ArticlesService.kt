package pl.kossa.nasa.app.server.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import pl.kossa.nasa.app.server.db.data.Article
import pl.kossa.nasa.app.server.db.repositories.ArticlesRepository
import pl.kossa.nasa.app.server.exceptions.NotFoundException
import java.util.Date

@Service("ArticlesService")
class ArticlesService {

    @Autowired
    protected lateinit var articlesRepository: ArticlesRepository

    @Autowired
    protected lateinit var nasaArticlesService: NASAArticlesService

    // TODO check move to date range and download missing from NASA
    fun getArticlesByPage(pageable: Pageable) = articlesRepository.findAll(pageable)

    suspend fun getArticleByDate(date: Date): Article {
        val dbArticle = articlesRepository.findById(date)
        if (dbArticle.isEmpty) {
            val nasaArticle = nasaArticlesService.getArticleByDate(date)
                ?: throw NotFoundException("Article from date $date not found")
            return articlesRepository.save(Article.fromNASAArticle(nasaArticle))
        }
        return dbArticle.get()
    }
}