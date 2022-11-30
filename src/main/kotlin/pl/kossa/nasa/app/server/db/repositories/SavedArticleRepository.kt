package pl.kossa.nasa.app.server.db.repositories

import org.springframework.data.repository.CrudRepository
import pl.kossa.nasa.app.server.db.data.SavedArticle
import java.util.*

interface SavedArticleRepository : CrudRepository<SavedArticle, Int> {

    suspend fun getAllByUserId(userId: String): List<SavedArticle>

    suspend fun deleteByUserIdAndArticleDate(userId: String, date: Date)

}