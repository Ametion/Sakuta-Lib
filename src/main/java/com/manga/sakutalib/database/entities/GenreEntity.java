package com.manga.sakutalib.database.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "genres")
public class GenreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String genre;

    @ManyToMany(mappedBy = "mangaGenres")
    private Set<MangaEntity> mangas;

    public GenreEntity() { }

    public GenreEntity(String genre) {
        this.genre = genre;
    }

    public GenreEntity(Long id, String genre, Set<MangaEntity> mangas) {
        this.id = id;
        this.genre = genre;
        this.mangas = mangas;
    }

    public Long getId() {
        return id;
    }

    public String getGenre() {
        return genre;
    }

    public Set<MangaEntity> getMangas() {
        return mangas;
    }
}
