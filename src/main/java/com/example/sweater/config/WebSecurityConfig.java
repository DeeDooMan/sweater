package com.example.sweater.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import javax.sql.DataSource;

//CTRL+ALT+O убрать все неиспользуемые импорты

//Этот класс при старте приложения конфигурирует WebSecurity
//система заходит в HttpSecurity http, передает на вход объект
//и мы в нем включаем след. функции :
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests() //Авторизация
                    .antMatchers("/", "/registration").permitAll() //Для главной страницы, в которую заходит пользователь мы разрешаем полный доступ
                    .anyRequest().authenticated() //Для всех остальных запросов мы требуем авторизацию
                .and()
                    .formLogin() //Включаем формЛогин
                    .loginPage("/login") //Указываем что логин пэйдж находится по пути \login
                    .permitAll() //Разрешаем пользоваться всем
                .and()
                    .logout() //Включаем logout
                    .permitAll(); //Разрешаем пользоваться всем
    }


    //Удалили, так как не хотим использовать мэнеджер,
    //а хотим брать пользователей из БАЗЫ ДАННЫх

    /*@Bean
    @Override

    //Будет выдаваться системе по требованию этого метода
    //Он создает в памяти Manager, который обслуживает учетные записи
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder() //Депликэйтед, нужен только для отладки
                        //При перезапуске приложения он создает заново пользователя
                        .username("user")
                        .password("password")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }*/

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource) //нужен для того, чтобы мэнэджер мог входить в базу данных искать пользователя и их роли
                .passwordEncoder(NoOpPasswordEncoder.getInstance()) //Шифровать пароли, чтобы они хранились в неявном виде
                //Для того чтобы система могла найти пользователя по его имени
                .usersByUsernameQuery("select username, password, active from usr where username=?")
                //Помогает спрингу получить список пользователей с их ролями,
                //соединенные через поля user_id и id выбираем поля username и roles
                //из таблицы user и присоединненной таблицы user_role
                .authoritiesByUsernameQuery("select u.username, ur.roles from usr u inner join user_role ur on u.id = ur.user_id where u.username=?");
    }
}