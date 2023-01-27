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

    @Column(nullable = false)
    private Integer pagesAmount;

    @Column(nullable = false)
    private String dateAdded;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn
    private MangaAuthorEntity author;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn
    private VolumeEntity volume;

    @OneToMany(mappedBy = "chapter")
    private List<MangaPageEntity> pages;

    public ChapterEntity() { }

    public ChapterEntity(String chapterName, Long chapterNumber, String dateAdded, MangaAuthorEntity author, VolumeEntity volume) {
        this.chapterName = chapterName;
        this.chapterNumber = chapterNumber;
        this.dateAdded = dateAdded;
        this.author = author;
        this.volume = volume;
        this.pagesAmount = 0;
    }

    public ChapterEntity(String chapterName, Long chapterNumber, String dateAdded, MangaAuthorEntity author, VolumeEntity volume, MangaPageEntity page) {
        this.chapterName = chapterName;
        this.chapterNumber = chapterNumber;
        this.dateAdded = dateAdded;
        this.author = author;
        this.volume = volume;
        this.pages = List.of(page);
        this.pagesAmount = 0;
    }

    public ChapterEntity(String chapterName, String dateAdded, MangaAuthorEntity author, VolumeEntity volume, Collection<MangaPageEntity> pages) {
        this.chapterName = chapterName;
        this.dateAdded = dateAdded;
        this.author = author;
        this.volume = volume;
        this.pages = (List<MangaPageEntity>) pages;
        this.pagesAmount = 0;
    }

    public ChapterEntity(Long id, String chapterName, Long chapterNumber, Integer pagesAmount, String dateAdded, MangaAuthorEntity author, VolumeEntity volume, List<MangaPageEntity> pages) {
        this.id = id;
        this.chapterName = chapterName;
        this.chapterNumber = chapterNumber;
        this.pagesAmount = pagesAmount;
        this.dateAdded = dateAdded;
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

    public Integer getPagesAmount() {
        return pagesAmount;
    }

    public void setPagesAmount(Integer pagesAmount) {
        this.pagesAmount = pagesAmount;
    }

    public String getDateAdded() {
        return dateAdded;
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