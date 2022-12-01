package pl.kossa.nasa.app.server.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pl.kossa.nasa.app.server.db.data.SavedArticle
import pl.kossa.nasa.app.server.db.repositories.SavedArticleRepository
import pl.kossa.nasa.app.server.errors.NotFoundError
import pl.kossa.nasa.app.server.exceptions.NotFoundException
import java.util.*

@Service("SavedArticleService")
class SavedArticleService {

    @Autowired
    protected lateinit var savedArticleRepository: SavedArticleRepository

    @Autowired
    protected lateinit var articlesService: ArticlesService

    @Autowired
    protected lateinit var userService: UserService

    suspend fun getByUserId(userId: String) = savedArticleRepository.getAllByUserId(userId)

    suspend fun save(userId: String, date: Date): SavedArticle {
        val user = userService.getUserById(userId) ?: throw NotFoundException("User not found")
        val article = articlesService.getArticleByDate(date)
        return savedArticleRepository.save(SavedArticle(0, article, user))
    }

    suspend fun deleteByUserIdAndDate(userId: String, date: Date) =
        savedArticleRepository.deleteByUserIdAndArticleDate(userId, date)
}