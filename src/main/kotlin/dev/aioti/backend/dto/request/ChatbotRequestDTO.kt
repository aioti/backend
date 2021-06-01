package dev.aioti.backend.dto.request

data class ChatbotRequestDTO(
    val text: String,
    val session: String,
)