package com.example.sweater.config;

import com.example.sweater.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests() //Авторизация
                    .antMatchers("/", "/registration", "/static/**").permitAll() //Для главной страницы, в которую заходит пользователь мы разрешаем полный доступ
                    .anyRequest().authenticated() //Для всех остальных запросов мы требуем авторизацию
                .and()
                    .formLogin() //Включаем формЛогин
                    .loginPage("/login") //Указываем что логин пэйдж находится по пути \login
                    .permitAll() //Разрешаем пользоваться всем
                .and()
                    .logout() //Включаем logout
                    .permitAll(); //Разрешаем пользоваться всем
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
}