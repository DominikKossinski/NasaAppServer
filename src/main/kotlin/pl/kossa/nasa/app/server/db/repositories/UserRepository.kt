package pl.kossa.nasa.app.server.db.repositories

import org.springframework.data.repository.CrudRepository
import pl.kossa.nasa.app.server.db.data.User

interface UserRepository: CrudRepository<User, String>