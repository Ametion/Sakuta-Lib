package com.manga.sakutalib.database.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "mangas")
public class MangaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String mangaName;

    @Column(nullable = false, unique = true)
    private String pathName;

    @Column(nullable = false)
    private String mangaDescription;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn
    private MangaAuthorEntity author;

    @OneToMany(mappedBy = "manga")
    private List<VolumeEntity> volumes;

    public MangaEntity() { }

    public MangaEntity(String mangaName, String pathName, String mangaDescription, MangaAuthorEntity author) {
        this.mangaName = mangaName;
        this.pathName = pathName;
        this.mangaDescription = mangaDescription;
        this.author = author;
    }

    public MangaEntity(Long id, String mangaName, String pathName, MangaAuthorEntity author, List<VolumeEntity> volumes) {
        this.id = id;
        this.mangaName = mangaName;
        this.pathName = pathName;
        this.author = author;
        this.volumes = volumes;
    }

    public Long getId() {
        return id;
    }

    public String getMangaName() {
        return mangaName;
    }

    public String getPathName() {
        return pathName;
    }

    public String getMangaDescription() {
        return mangaDescription;
    }

    public MangaAuthorEntity getAuthor() {
        return author;
    }

    public List<VolumeEntity> getVolumes() {
        return volumes;
    }
}