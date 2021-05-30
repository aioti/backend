package dev.aioti.backend.controller

import com.google.cloud.dialogflow.v2.*
import dev.aioti.backend.dto.request.ChatbotRequestDTO
import dev.aioti.backend.dto.response.ChatbotResponseDTO
import dev.aioti.backend.service.ChatbotService
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1/chatbot")
class ChatbotController(
    private val chatbotService: ChatbotService
) {

    @PostMapping
    fun index(@RequestBody chatbotRequestDTO: ChatbotRequestDTO) =
        chatbotService.index(chatbotRequestDTO)

}
