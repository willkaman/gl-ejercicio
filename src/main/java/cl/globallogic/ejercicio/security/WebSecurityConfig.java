package cl.globallogic.ejercicio.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    JWTAuthorizationFilter filter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http// .csrf().disable()
            // .addFilterAfter(new JWTAuthorizationFilter(),
            // UsernamePasswordAuthenticationFilter.class)
            .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/authme").permitAll()
                .antMatchers(HttpMethod.POST,"/usuario").permitAll()
                .antMatchers(HttpMethod.POST,"/usuario/**").permitAll()
                .antMatchers(HttpMethod.GET,"/usuario/**").authenticated()
                //.antMatchers(HttpMethod.POST,"/**").permitAll()
                //.antMatchers(HttpMethod.GET,"/**").permitAll()
                //.antMatchers(HttpMethod.POST,"/usuario/**").authenticated()
                //.antMatchers(HttpMethod.GET,"/usuario/**").authenticated()
        // .anyRequest().authenticated();
        .and().csrf().disable();

        http.addFilterAfter(filter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
