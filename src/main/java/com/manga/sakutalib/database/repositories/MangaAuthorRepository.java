package com.manga.sakutalib.database.repositories;

import com.manga.sakutalib.database.entities.MangaAuthorEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface MangaAuthorRepository extends CrudRepository<MangaAuthorEntity, Long> {
    Collection<MangaAuthorEntity> findByFirstName(String firstName);
}