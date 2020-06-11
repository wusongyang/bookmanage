package com.songyang.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
public class SecurityConifg extends WebSecurityConfigurerAdapter {
    @Autowired
    private LoginSuccessHandler loginSuccessHandler;
    @Autowired
    private ServiceUserdetil serviceUserdetil;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    LoginFailedHandler loginFailedHandler;
    @Autowired
    AjaxAccessDeniedHandler ajaxAccessDeniedHandler;

    @Autowired
    AjaxAuthenticationEntryPoint ajaxAuthenticationEntryPoint;
    @Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(serviceUserdetil).passwordEncoder(getPasswordEncodeer());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().
                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().
                formLogin().loginProcessingUrl("/login").
                successHandler(loginSuccessHandler).
                failureHandler(loginFailedHandler).
                and().
                authorizeRequests().
                antMatchers("/admin/**").hasRole("admin").
                antMatchers("/employee/**").hasRole("employee").
                antMatchers("/login").permitAll().
                antMatchers("/hello").permitAll().
                antMatchers("/user/register").permitAll().
                antMatchers("/v2/api-docs", "/swagger-resources/configuration/ui",
                "/swagger-resources", "/swagger-resources/configuration/security",
                "/swagger-ui.html", "/webjars/**").permitAll().
                antMatchers("/visitor/**").permitAll().
                anyRequest().authenticated();
             http.exceptionHandling().accessDeniedHandler(ajaxAccessDeniedHandler).
                     authenticationEntryPoint(ajaxAuthenticationEntryPoint);

                http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }


    @Bean
    public BCryptPasswordEncoder getPasswordEncodeer(){
        return  new BCryptPasswordEncoder();
    }

}

