package com.manga.sakutalib.database.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "manga_page_comments")
public class MangaPageCommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String createdAt;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(nullable = false)
    private UserEntity author;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(nullable = false)
    private MangaPageEntity mangaPage;

    public MangaPageCommentEntity() { }

    public MangaPageCommentEntity(String content, String createdAt, UserEntity author, MangaPageEntity mangaPage) {
        this.content = content;
        this.createdAt = createdAt;
        this.author = author;
        this.mangaPage = mangaPage;
    }

    public MangaPageCommentEntity(Long id, String content, String createdAt, UserEntity author, MangaPageEntity mangaPage) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.author = author;
        this.mangaPage = mangaPage;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public MangaPageEntity getMangaPage() {
        return mangaPage;
    }
}