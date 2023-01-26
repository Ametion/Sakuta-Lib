package com.manga.sakutalib.database.repositories;

import com.manga.sakutalib.database.entities.GenreEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Set;

public interface GenreRepository extends CrudRepository<GenreEntity, Long> {
    Set<GenreEntity> findByIdIn(Collection<Long> ids);
}
