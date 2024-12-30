package general.springboothomework.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import general.springboothomework.dto.ErrorDto;
import jakarta.servlet.ServletOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.ArrayList;
import java.util.List;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfigurer {

    @Autowired
    private ObjectMapper jacksonObjectMapper;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.cors(AbstractHttpConfigurer::disable);
        http.csrf(AbstractHttpConfigurer::disable);

        http.authorizeHttpRequests(secure -> secure
//                .requestMatchers(HttpMethod.GET, "/**").permitAll()
//                        .requestMatchers("/user/register", "/user/").permitAll()
                        .requestMatchers("/**").permitAll()
                        .anyRequest().authenticated()
        );

        http.exceptionHandling(exe -> {
            exe
                    .authenticationEntryPoint(authenticationEntryPoint());
        });
        http.httpBasic(Customizer.withDefaults()
        );

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOriginPatterns(List.of(
                "http://localhost:8080",
                "http://localhost:9090",
                "http://localhost:9095"));
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, authException) -> {
            String requestURI = request.getRequestURI();
            String message = authException.getMessage();
            int errorCode = 401;
            ErrorDto dto = new ErrorDto();
            dto.setErrorPath(requestURI);
            dto.setErrorMessage(message);
            dto.setErrorCode(errorCode);
            response.setStatus(errorCode);
            ServletOutputStream outputStream = response.getOutputStream();
            jacksonObjectMapper.writeValue(outputStream, dto);
        };
    }


}
