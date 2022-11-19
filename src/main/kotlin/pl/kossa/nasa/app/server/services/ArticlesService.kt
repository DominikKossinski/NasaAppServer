package pl.kossa.nasa.app.server.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pl.kossa.nasa.app.server.db.repositories.ArticlesRepository
import java.util.Date

@Service("ArticlesService")
class ArticlesService {

    @Autowired
    protected lateinit var articlesRepository: ArticlesRepository

    fun getArticleByDate(date: Date) = articlesRepository.findById(date)
}