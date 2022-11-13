package pl.kossa.nasa.app.server.db.data

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "Users")
data class User(
    @Id
    @Column(name="id", columnDefinition = "varchar(30)")
    val id: String,

    @Column(name="email", columnDefinition = "varchar(200)")
    val email: String,
)