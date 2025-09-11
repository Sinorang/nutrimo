/**
 * Spring Security configuration
 * spring-boot-starter-security를 추가하면 스프링은 기본 폼 로그인 페이지와 세션 기반 보안을 on → 브라우저로 /api 접속 시 /api/login 리다이렉트
 * JWT 기반의 REST API를 만들 거라 폼 로그인/세션이 필요 없고, 인증 안 된 요청은 리다이렉트 대신 401 JSON으로 응답해야 함
 *
 * 핵심 개념 4가지
 * 	1.	formLogin / httpBasic 비활성화: 브라우저 로그인 화면/Basic Auth 끄기
 * 	2.	STATELESS: 세션 안 씀(JWT 전제)
 * 	3.	permitAll 경로: 인증 없이 열어둘 API(예: /auth/**, 헬스체크 등)
 * 	4.	401/403 JSON 응답: 리다이렉트 대신 JSON 에러 바디로 응답
 */
package com.github.sinorang.nutrimo.admin.common.security;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * API 서버 기본 보안 정책:
     * - 세션 사용 안 함(STATELESS)
     * - 폼 로그인/HTTP Basic 비활성화(리다이렉트 방지)
     * - 인증 실패/인가 실패 시 401/403 JSON으로 응답
     * - 인증 없이 허용할 경로는 permitAll
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // CSRF: 세션/폼 사용 안 하므로 비활성화 (JWT 사용 시 일반적으로 끔)
                .csrf(csrf -> csrf.disable())

                // 우리는 세션을 만들지 않음(= JWT 인증 전제)
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // 인증/인가 실패 시 리다이렉트 대신 JSON 바디로 응답
                .exceptionHandling(eh -> eh
                        .authenticationEntryPoint((req, res, ex) -> {
                            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
                            res.setContentType("application/json");
                            res.getWriter().write("{\"error\":\"unauthorized\"}");
                        })
                        .accessDeniedHandler((req, res, ex) -> {
                            res.setStatus(HttpServletResponse.SC_FORBIDDEN); // 403
                            res.setContentType("application/json");
                            res.getWriter().write("{\"error\":\"forbidden\"}");
                        })
                )

                // 기본 로그인 화면/Basic 인증 끄기 → 더 이상 /login 리다이렉트 없음
                .formLogin(Customizer.withDefaults()).formLogin(form -> form.disable())
                .httpBasic(Customizer.withDefaults()).httpBasic(basic -> basic.disable())

                // URL 접근 규칙
                .authorizeHttpRequests(auth -> auth
                        // 여기는 인증 없이 허용 (추가하고 싶은 공개 경로를 여기에)
                        .requestMatchers(
                                // api-unauthenticated
                                "/hello",

                                // default
                                "/auth/**",
                                "/actuator/health",
                                "/error",
                                "/swagger-ui/**",
                                "/v3/api-docs/**"
                        ).permitAll()

                        // 그 외 모든 요청은 인증 필요
                        .anyRequest().authenticated()
                );

        // ★ JWT 도입 시 여기에 필터를 추가하게 됩니다.
        // http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /**
     * 비밀번호 해시용 인코더. 회원가입/로그인 때 사용.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
