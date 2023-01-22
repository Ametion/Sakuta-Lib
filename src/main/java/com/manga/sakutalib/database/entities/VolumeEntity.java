package com.manga.sakutalib.database.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "volumes")
public class VolumeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer volumeNumber;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(nullable = false)
    private MangaEntity manga;

    @OneToMany(mappedBy = "volume")
    private List<ChapterEntity> chapters;

    public VolumeEntity() { }

    public VolumeEntity(Integer volumeNumber, MangaEntity manga) {
        this.volumeNumber = volumeNumber;
        this.manga = manga;
    }

    public VolumeEntity(Integer volumeNumber, MangaEntity manga, ChapterEntity chapter) {
        this.volumeNumber = volumeNumber;
        this.manga = manga;
        this.chapters = List.of(chapter);
    }

    public VolumeEntity(Integer volumeNumber, MangaEntity manga, List<ChapterEntity> chapters) {
        this.volumeNumber = volumeNumber;
        this.manga = manga;
        this.chapters = chapters;
    }

    public VolumeEntity(Long id, Integer volumeNumber, MangaEntity manga, List<ChapterEntity> chapters) {
        this.id = id;
        this.volumeNumber = volumeNumber;
        this.manga = manga;
        this.chapters = chapters;
    }

    public Long getId() {
        return id;
    }

    public Integer getVolumeNumber() {
        return volumeNumber;
    }

    public MangaEntity getManga() {
        return manga;
    }

    public List<ChapterEntity> getChapters() {
        return chapters;
    }

    public void setChapters(List<ChapterEntity> chapters) {
        this.chapters = chapters;
    }
}
