package dev.aioti.backend.config.filter

import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.util.ContentCachingRequestWrapper
import org.springframework.web.util.ContentCachingResponseWrapper
import java.nio.charset.Charset
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class LoggingFilter : OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {

        if (isAsyncDispatch(request))
            chain.doFilter(request, response)
        else
            doFilterWrapped(ContentCachingRequestWrapper(request), ContentCachingResponseWrapper(response), chain)

    }

    private fun doFilterWrapped(
        request: ContentCachingRequestWrapper,
        response: ContentCachingResponseWrapper,
        filterChain: FilterChain
    ) {
        try {
            filterChain.doFilter(request, response)
        } finally {
            try {
                logRequest(request)
                logResponse(response)
            } finally {
                response.copyBodyToResponse()
            }
        }
    }

    private fun logRequest(request: ContentCachingRequestWrapper) {
        println("===== Request:")
        println("\tUrl: ${request.requestURI}")
        println("\tMethod: ${request.method}")
        println("\tHeaders: ")
        logRequestHeaders(request)
        logContent(request.contentAsByteArray, request.characterEncoding)
    }

    private fun logRequestHeaders(
        request: ContentCachingRequestWrapper,
    ) {
        request.headerNames.asIterator().forEach {
            println("\t\t$it ${request.getHeader(it)}")
        }
    }

    private fun logRequestHeaders(
        response: ContentCachingResponseWrapper,
    ) {
        response.headerNames.forEach {
            println("\t\t$it ${response.getHeader(it)}")
        }
    }

    private fun logResponse(response: ContentCachingResponseWrapper) {
        println("===== Response:")
        println("\tStatus: ${response.status}")
        println("\tHeaders:")
        logRequestHeaders(response)
        logContent(response.contentAsByteArray, response.characterEncoding)
    }

    private fun logContent(byteArray: ByteArray, encoding: String) {
        try {
            val content = String(byteArray, Charset.forName(encoding))
            if (content.isNotBlank())
                println(GsonBuilder().setPrettyPrinting().create().toJson(JsonParser().parse(content)))
        } catch (e: Exception) {}
    }
}
