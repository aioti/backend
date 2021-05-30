package dev.aioti.backend.service

import com.google.cloud.dialogflow.v2.QueryInput
import com.google.cloud.dialogflow.v2.SessionName
import com.google.cloud.dialogflow.v2.SessionsClient
import com.google.cloud.dialogflow.v2.TextInput
import dev.aioti.backend.dto.request.ChatbotRequestDTO
import dev.aioti.backend.dto.response.ChatbotResponseDTO
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody

@Service
class ChatbotService {

    @Value("\${dialogflow.project.id}")
    private lateinit var projectId: String
    private val languageCode = "pt-BR"

    fun index(@RequestBody chatbotRequestDTO: ChatbotRequestDTO): ChatbotResponseDTO {
        val session = SessionName.of(projectId, chatbotRequestDTO.session)

        val sessionsClient = SessionsClient.create()

        val textInput = TextInput
            .newBuilder()
            .setText(chatbotRequestDTO.text)
            .setLanguageCode(languageCode)

        val queryInput = QueryInput
            .newBuilder()
            .setText(textInput)
            .build()

        val response = sessionsClient.detectIntent(session, queryInput)

        return ChatbotResponseDTO(response.queryResult.fulfillmentText)
    }

}