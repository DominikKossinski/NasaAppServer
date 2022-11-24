package pl.kossa.nasa.app.server.services

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import pl.kossa.nasa.app.server.db.data.Article
import pl.kossa.nasa.app.server.db.repositories.ArticlesRepository
import pl.kossa.nasa.app.server.exceptions.NotFoundException
import java.time.temporal.ChronoUnit
import java.util.Date

@Service("ArticlesService")
class ArticlesService {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @Autowired
    protected lateinit var articlesRepository: ArticlesRepository

    @Autowired
    protected lateinit var nasaArticlesService: NASAArticlesService

    suspend fun getArticlesByPage(from: Date, to: Date): List<Article> {
        val articles = articlesRepository.findAllBetween(from, to)
        if (articles.size != (ChronoUnit.DAYS.between(from.toInstant(), to.toInstant()) + 1).toInt()) {
            val nasaArticles = nasaArticlesService.getArticlesByDateRange(from, to)
            val mappedArticles = nasaArticles.map { Article.fromNASAArticle(it) }
            return articlesRepository.saveAll(mappedArticles).toList()
        }
        return articles
    }

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