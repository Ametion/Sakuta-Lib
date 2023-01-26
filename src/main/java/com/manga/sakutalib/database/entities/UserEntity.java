package com.manga.sakutalib.database.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String secondName;

    @Column(nullable = false)
    private String nickName;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String password;

    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(name = "favourite_mangas_user",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "manga_id"))
    private Set<MangaEntity> favouriteMangas;

    public UserEntity() { }

    public UserEntity(String firstName, String secondName, String login, String password) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.login = login;
        this.password = password;
    }

    public UserEntity(String firstName, String secondName, String nickName, String login, String password) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.nickName = nickName;
        this.login = login;
        this.password = password;
    }

    public UserEntity(Long id, String firstName, String secondName, String nickName, String login, String password) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.nickName = nickName;
        this.login = login;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
