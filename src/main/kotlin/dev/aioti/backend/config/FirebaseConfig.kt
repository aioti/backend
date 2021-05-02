package dev.aioti.backend.config

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import java.io.FileInputStream

@Configuration
class FirebaseConfig {


    @Primary
    @Bean
    fun firebaseInit() {

        val file = FileInputStream("./admin-credentials.json")

        val credentials = GoogleCredentials.fromStream(file)

        val firebaseOptions = FirebaseOptions.builder()
            .setCredentials(credentials)
            .build()

        if (FirebaseApp.getApps().isEmpty())
            FirebaseApp.initializeApp(firebaseOptions)
    }

}
