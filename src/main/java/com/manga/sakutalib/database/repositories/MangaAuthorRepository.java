package com.manga.sakutalib.database.repositories;

import com.manga.sakutalib.database.entities.MangaAuthorEntity;
import org.springframework.data.repository.CrudRepository;

public interface MangaAuthorRepository extends CrudRepository<MangaAuthorEntity, Long> { }