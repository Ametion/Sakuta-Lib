package com.manga.sakutalib.database.repositories;

import com.manga.sakutalib.database.entities.MangaEntity;
import org.springframework.data.repository.CrudRepository;

public interface MangaRepository extends CrudRepository<MangaEntity, Long> { }