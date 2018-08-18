package uk.ac.ebi.codetest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Spring security configuration
 *
 * @author Venkaiah Chowdary Koneru
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Configures the HTTP security with stateless session as we will be using HTTP Basic auth mechanism.
     * <p>
     * {@inheritDoc}
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable() // Disabling CSRF as we will be using HTTP Basic
                .authorizeRequests()

                .antMatchers("/swagger-ui.html",
                        "/webjars/springfox-swagger-ui/**",
                        "/swagger-resources/**",
                        "/v2/api-docs")
                .permitAll() // permit all swagger related content with no auth
                .anyRequest().authenticated()

                .and()
                .httpBasic()

                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    /**
     * configures an in memory authentication manager. In the real world app, We shall have a custom service that
     * fetches users from a store (RDBMS or NoSQL)
     *
     * @param auth authentication manager to configure
     *
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        String password = passwordEncoder().encode("password");

        auth.inMemoryAuthentication()
                .withUser("user")
                .password(password)
                .roles("USER")

                .and()

                .withUser("user2")
                .password(password)
                .roles("USER")
                .authorities("READ_PRIVILEGES")

                .and()

                .withUser("manager")
                .password(password)
                .roles("MANAGER")
                .authorities("WRITE_PRIVILEGES", "READ_PRIVILEGES");
    }

    /**
     * Password encoder bean
     *
     * @return a PasswordEncoder that uses the BCrypt strong hashing function
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
