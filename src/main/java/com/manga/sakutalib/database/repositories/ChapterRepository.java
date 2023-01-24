package com.manga.sakutalib.database.repositories;

import com.manga.sakutalib.database.entities.ChapterEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface ChapterRepository extends CrudRepository<ChapterEntity, Long> {
    Collection<ChapterEntity> findAllByVolumeMangaId(Long mangaId);
}