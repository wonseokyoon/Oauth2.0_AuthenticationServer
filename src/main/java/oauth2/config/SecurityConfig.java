package oauth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain authorizationServer(HttpSecurity http) throws Exception {
        OAuth2AuthorizationServerConfigurer oauth2Config = new OAuth2AuthorizationServerConfigurer();

        // 여기에서 OAuth2 권한 서버의 설정을 추가합니다.
        oauth2Config.authorizationEndpoint(withDefaults());
        oauth2Config.tokenEndpoint(withDefaults());
        oauth2Config.oidc(withDefaults()); // OIDC 지원 (선택 사항)

        // HttpSecurity에 OAuth2AuthorizationServerConfigurer 적용
        oauth2Config.configure(http);

        http.exceptionHandling(exceptions ->
                exceptions.defaultAuthenticationEntryPointFor(
                        new LoginUrlAuthenticationEntryPoint("/login"),
                        new MediaTypeRequestMatcher(MediaType.TEXT_HTML)
                )
        );

        return http.build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf((csrf) -> csrf.disable());

        http
                .authorizeHttpRequests((auth) -> auth
                        .anyRequest().permitAll());

        http
                .formLogin(withDefaults());

        return http.build();
    }

    @Bean
    public AuthorizationServerSettings authorizationServerSettings() {

        return AuthorizationServerSettings.builder()
//                .issuer("http://localhost:9000")
//                .authorizationEndpoint("/oauth2/v1/authorize")
//                .tokenEndpoint("/oauth2/v1/token")
//                .tokenIntrospectionEndpoint("/oauth2/v1/introspect") // 토큰 상태
//                .tokenRevocationEndpoint("/oauth2/v1/revoke") // 토큰 폐기 RFC 7009
//                .jwkSetEndpoint("/oauth2/v1/jwks") // 공개키 확인
//                .oidcLogoutEndpoint("/connect/v1/logout")
//                .oidcUserInfoEndpoint("/connect/v1/userinfo") // 리소스 서버 유저 정보 연관
//                .oidcClientRegistrationEndpoint("/connect/v1/register") // OAuth2 사용 신청
                .build();
    }




}
