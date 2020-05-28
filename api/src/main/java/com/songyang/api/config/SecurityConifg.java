package com.songyang.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


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

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(serviceUserdetil).passwordEncoder(getPasswordEncodeer());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().
                formLogin().loginProcessingUrl("/login").
                successHandler(loginSuccessHandler).
                failureHandler(loginFailedHandler).
                and().
                authorizeRequests().
                antMatchers("/admin/**").hasRole("admin").
                antMatchers("/login").permitAll().
                antMatchers("/hello").permitAll().
                anyRequest().authenticated();
             http.exceptionHandling().accessDeniedHandler(ajaxAccessDeniedHandler).authenticationEntryPoint(ajaxAuthenticationEntryPoint);
    }


    @Bean
    public BCryptPasswordEncoder getPasswordEncodeer(){
        return  new BCryptPasswordEncoder();
    }

}
