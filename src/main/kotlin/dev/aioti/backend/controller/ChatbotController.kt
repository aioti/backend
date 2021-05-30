package dev.aioti.backend.controller

import com.google.cloud.dialogflow.v2.*
import dev.aioti.backend.dto.request.ChatbotRequestDTO
import dev.aioti.backend.dto.response.ChatbotResponseDTO
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1/chatbot")
class ChatbotController {

    @Value("\${dialogflow.project.id}")
    private lateinit var projectId: String
    private val languageCode = "pt-BR"

    @PostMapping
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
