package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
@Configuration
//to use @preAuthorize, @postAuthorized
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
    {
        //auth.jdbcAuthentication()
        auth.inMemoryAuthentication()
                .withUser("user")
                .password("{noop}test")
                .roles("USER");
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("{noop}test2")
                .roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                /*
                /test/** : 인증 되어야 함.
                /user/** : 누구나 접속할 수 있음. (permitAll)
                 */
                .and()
                .authorizeRequests()
                .antMatchers("/test/**").authenticated()
                .antMatchers("/user/**").permitAll()
                .and().formLogin() //form login 활성화
                .and().csrf().disable(); // csrf disable
    }
}
