package com.example.sweater.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "usr") // usr так как в postgreSQL уже есть ключевое слово user
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private boolean active;


    //Ролевая система, например админ-пользователь или модератор
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER) //Формирование дополнительной таблицы для хранения enum
    //fetch - это параметр, который определяет как данные значений будут подгружаться относительно основной сущности
    //когда мы загружаем пользователя, его роли хранятся в отдельной таблице
    //и нам необходимо загружать их ЖАДНЫМ способом или ЛЕНИВЫМ
    //ЖАДНЫЙ - когда гибернэйт сразу же при запросе пользователя
    //будет подгружать все его роли.
    //ЛЕНИВЫЙ - подгрузит роли, только когда пользователь реально обратится к этому полю
    //EAGER(жадный) хорош когда у нас мало данных, в нашем случае с ролями,
    //ускоряет работу, но потребляет больше памяти
    //LAZY(ленывый) хорош когда много записей(например: класс иснтитут, который содержит 1000 студентов)


    //Описывает что данное поле будет хранится в отдельной таблице,
    //для которой мы не описывали мэппинг
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    //Позволяет создать таблицу для набора ролей, таблица user_role
    //которая будет соединяться с текущей таблицей через user_id

    //Хотим хранить enum в виде string
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
