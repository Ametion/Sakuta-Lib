package com.manga.sakutalib.database.repositories;

import com.manga.sakutalib.database.entities.ChapterEntity;
import org.springframework.data.repository.CrudRepository;

public interface ChapterRepository extends CrudRepository<ChapterEntity, Long> { }