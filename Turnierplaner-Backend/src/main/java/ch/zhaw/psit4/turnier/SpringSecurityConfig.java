package ch.zhaw.psit4.turnier;

import ch.zhaw.psit4.turnier.persistence.service.UserService;
import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@ComponentScan(basePackages = {"ch.zhaw.psit4.turnier"})
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        if(true){
            http.authorizeRequests()
                    .antMatchers("/register", "/login",
                            "/swagger-ui.html", "/webjars/**",
                            "/swagger-resources", "/v2/api-docs",
                            "/configuration/ui", "/configuration/security",
                            "/swagger-resources/configuration/ui", "/swagger-resources/configuration/security")
                    .permitAll();
            super.configure(http);
        }

        http.cors();
        http.csrf().disable();

        http.formLogin()
                .failureHandler((request, response, authentication) -> response.setStatus(401))
                .successHandler((request, response, authentication) -> response.setStatus(200))
                .and().logout().logoutSuccessHandler((request, response, authentication) -> response.setStatus(200))
                .and().exceptionHandling().authenticationEntryPoint(authenticationEntryPoint());
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(11);
    }

    @Bean
    public ch.zhaw.psit4.turnier.persistence.security.AuthenticationEntryPoint authenticationEntryPoint() {
        return new ch.zhaw.psit4.turnier.persistence.security.AuthenticationEntryPoint("/login");
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(ImmutableList.of("*"));
        configuration.setAllowedMethods(ImmutableList.of("HEAD",
                "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(ImmutableList.of("Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
                "Access-Control-Request-Headers", "withCredentials", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", "Cookie"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
