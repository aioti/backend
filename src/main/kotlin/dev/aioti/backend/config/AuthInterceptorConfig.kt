package dev.aioti.backend.config

import dev.aioti.backend.dto.CurrentUserDTO
import dev.aioti.backend.config.interceptor.AuthInterceptor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope
import org.springframework.context.annotation.ScopedProxyMode
import org.springframework.util.AntPathMatcher
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class AuthInterceptorConfig: WebMvcConfigurer {

    @Bean
    @Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
    fun currentUser(): CurrentUserDTO {
        return CurrentUserDTO()
    }

    @Bean
    fun authInterceptorProvider() = AuthInterceptor()

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry
            .addInterceptor(authInterceptorProvider())
            .excludePathPatterns("/api/*/user/register")
    }
}
