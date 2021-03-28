package pl.coderslab.CharityDonateApp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.coderslab.CharityDonateApp.services.UserDetailsServiceImp;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public UserDetailsServiceImp customUserDetailsService() {
        return new UserDetailsServiceImp();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/form").hasAnyRole("USER", "ADMIN")
                .antMatchers("/", "/login", "/user/form").permitAll()
                .and().formLogin().loginPage("/login")
                .usernameParameter("email")
                .defaultSuccessUrl("/", true)
                .and().logout()
                .logoutSuccessUrl("/");
    }

}