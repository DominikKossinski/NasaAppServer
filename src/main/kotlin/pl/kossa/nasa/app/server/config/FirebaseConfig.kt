package pl.kossa.nasa.app.server.config

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component
import java.io.FileInputStream

@Component
class FirebaseConfig {

    @Bean
    @Primary
    fun firebaseApp() {
        val options = FirebaseOptions.builder()
        val serviceAccount = FileInputStream(System.getenv(GOOGLE_APPLICATION_CREDENTIALS))
        options.setCredentials(GoogleCredentials.fromStream(serviceAccount))
        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options.build())
        }
    }

    companion object {
        private const val GOOGLE_APPLICATION_CREDENTIALS = "GOOGLE_APPLICATION_CREDENTIALS"
    }
}