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

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn
    private MangaAuthorEntity author;

    @OneToMany(mappedBy = "manga")
    private List<VolumeEntity> volumes;

    public MangaEntity(String mangaName, MangaAuthorEntity author) {
        this.mangaName = mangaName;
        this.author = author;
    }

    public MangaEntity() { }

    public MangaEntity(String mangaName, MangaAuthorEntity author, List<VolumeEntity> volumes) {
        this.mangaName = mangaName;
        this.author = author;
        this.volumes = volumes;
    }

    public MangaEntity(Long id, String mangaName, MangaAuthorEntity author, List<VolumeEntity> volumes) {
        this.id = id;
        this.mangaName = mangaName;
        this.author = author;
        this.volumes = volumes;
    }

    public Long getId() {
        return id;
    }

    public String getMangaName() {
        return mangaName;
    }

    public MangaAuthorEntity getAuthor() {
        return author;
    }

    public List<VolumeEntity> getVolumes() {
        return volumes;
    }
}