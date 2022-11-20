package pl.kossa.nasa.app.server.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import pl.kossa.nasa.app.server.db.repositories.ArticlesRepository
import pl.kossa.nasa.app.server.exceptions.NotFoundException
import java.util.Date

@Service("ArticlesService")
class ArticlesService {

    @Autowired
    protected lateinit var articlesRepository: ArticlesRepository

    // TODO check move to date range and download missing from NASA
    fun getArticlesByPage(pageable: Pageable) = articlesRepository.findAll(pageable)

    // TODO download article from NASA if not in DB
    fun getArticleByDate(date: Date) =
        articlesRepository.findByIdOrNull(date) ?: throw NotFoundException("Article from date $date not found")
}