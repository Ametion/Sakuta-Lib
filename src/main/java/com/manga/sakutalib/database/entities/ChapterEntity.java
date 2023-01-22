package com.manga.sakutalib.database.entities;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "chapters")
public class ChapterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String chapterName;

    @Column(nullable = false)
    private Long chapterNumber;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn
    private MangaAuthorEntity author;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn
    private VolumeEntity volume;

    @OneToMany(mappedBy = "chapter")
    private List<MangaPageEntity> pages;

    public ChapterEntity() { }

    public ChapterEntity(String chapterName, Long chapterNumber, MangaAuthorEntity author, VolumeEntity volume) {
        this.chapterName = chapterName;
        this.chapterNumber = chapterNumber;
        this.author = author;
        this.volume = volume;
    }

    public ChapterEntity(String chapterName, Long chapterNumber, MangaAuthorEntity author, VolumeEntity volume, MangaPageEntity page) {
        this.chapterName = chapterName;
        this.chapterNumber = chapterNumber;
        this.author = author;
        this.volume = volume;
        this.pages = List.of(page);
    }

    public ChapterEntity(String chapterName, MangaAuthorEntity author, VolumeEntity volume, Collection<MangaPageEntity> pages) {
        this.chapterName = chapterName;
        this.author = author;
        this.volume = volume;
        this.pages = (List<MangaPageEntity>) pages;
    }

    public ChapterEntity(Long id, String chapterName, MangaAuthorEntity author, VolumeEntity volume, List<MangaPageEntity> pages) {
        this.id = id;
        this.chapterName = chapterName;
        this.author = author;
        this.volume = volume;
        this.pages = pages;
    }

    public Long getId() {
        return id;
    }

    public String getChapterName() {
        return chapterName;
    }

    public Long getChapterNumber() {
        return chapterNumber;
    }

    public MangaAuthorEntity getAuthor() {
        return author;
    }


    public VolumeEntity getVolume() {
        return volume;
    }

    public List<MangaPageEntity> getPages() {
        return pages;
    }

    public void setPages(List<MangaPageEntity> pages) {
        this.pages = pages;
    }
}