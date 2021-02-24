package com.ssutudy.admin.config;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.UUID;

@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final AdminServerProperties adminServer;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        successHandler.setTargetUrlParameter("redirectTo");
        successHandler.setDefaultTargetUrl(this.adminServer.getContextPath()+"/");

        http
            .authorizeRequests()
                .antMatchers(this.adminServer.getContextPath()+"/assets/**").permitAll()
                .antMatchers(this.adminServer.getContextPath()+"/**/*.css").permitAll()
                .antMatchers(this.adminServer.getContextPath()+"/login").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .successHandler(successHandler)
                .and()
            .logout()
                .logoutUrl(this.adminServer.getContextPath()+"/logout")
                .and()
            .httpBasic()
                .and()
            .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .ignoringRequestMatchers(
                    new AntPathRequestMatcher(this.adminServer.getContextPath()+"/instances"),
                    new AntPathRequestMatcher(this.adminServer.getContextPath()+"/instances", HttpMethod.POST.toString()),
                    new AntPathRequestMatcher(this.adminServer.getContextPath()+"/instances/*", HttpMethod.DELETE.toString()),
                    new AntPathRequestMatcher(this.adminServer.getContextPath()+"/actuator/**")
                );
    }
}
