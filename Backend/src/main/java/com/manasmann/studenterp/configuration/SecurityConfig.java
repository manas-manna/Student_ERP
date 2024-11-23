package com.manasmann.studenterp.configuration;

import com.manasmann.studenterp.helper.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig  implements WebMvcConfigurer {
    private final RequestInterceptor requestInterceptor;

        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            // Apply the interceptor to all endpoints except /auth/login
            registry.addInterceptor(requestInterceptor)
                    .addPathPatterns("/**")
                    .excludePathPatterns("/**","/api/v1/payments/**","/api/v1/auth/**","/api/v1/admins","/api/v1/students","/api/v1/bills/**");
        }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
