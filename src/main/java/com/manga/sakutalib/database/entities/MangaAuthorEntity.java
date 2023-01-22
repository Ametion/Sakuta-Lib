package com.manga.sakutalib.database.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "manga_authors")
public class MangaAuthorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String secondName;

    @Column(nullable = false)
    private Date birthday;

    @OneToMany(mappedBy = "author")
    private List<ChapterEntity> chapters;

    @OneToMany(mappedBy = "author")
    private List<MangaEntity> mangas;

    public MangaAuthorEntity() { }

    public MangaAuthorEntity(String firstName, String secondName, Date birthday) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthday = birthday;
    }

    public MangaAuthorEntity(String firstName, String secondName, Date birthday, List<ChapterEntity> chapters, List<MangaEntity> mangas) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthday = birthday;
        this.chapters = chapters;
        this.mangas = mangas;
    }

    public MangaAuthorEntity(Long id, String firstName, String secondName, Date birthday, List<ChapterEntity> chapters, List<MangaEntity> mangas) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthday = birthday;
        this.chapters = chapters;
        this.mangas = mangas;
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

    public Date getBirthday() {
        return birthday;
    }

    public List<ChapterEntity> getChapters() {
        return chapters;
    }

    public List<MangaEntity> getMangas() {
        return mangas;
    }
}
