package com.manga.sakutalib.database.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "manga_pages")
public class MangaPageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer pageNumber;

    @Column(nullable = false)
    private String path;

    @OneToMany(mappedBy = "mangaPage")
    private Set<MangaPageCommentEntity> comments;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(nullable = false)
    private ChapterEntity chapter;

    public MangaPageEntity() { }

    public MangaPageEntity(Integer pageNumber, String path, ChapterEntity chapter) {
        this.pageNumber = pageNumber;
        this.path = path;
        this.chapter = chapter;
    }

    public MangaPageEntity(Long id, Integer pageNumber, String path, Set<MangaPageCommentEntity> comments, ChapterEntity chapter) {
        this.id = id;
        this.pageNumber = pageNumber;
        this.path = path;
        this.comments = comments;
        this.chapter = chapter;
    }

    public Long getId() {
        return id;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public String getPath() {
        return path;
    }

    public Set<MangaPageCommentEntity> getComments() {
        return comments;
    }
    public ChapterEntity getChapter() {
        return chapter;
    }
}
