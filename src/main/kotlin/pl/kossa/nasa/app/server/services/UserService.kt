package pl.kossa.nasa.app.server.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import pl.kossa.nasa.app.server.db.data.User
import pl.kossa.nasa.app.server.db.repositories.UserRepository

@Service("UserService")
class UserService {

    @Autowired
    protected lateinit var userRepository: UserRepository

    suspend fun getUserById(id: String) = userRepository.findByIdOrNull(id)

    fun save(user: User) = userRepository.save(user)
}