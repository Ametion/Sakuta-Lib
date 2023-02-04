package com.manga.sakutalib.database.repositories;

import com.manga.sakutalib.database.entities.MangaPageCommentEntity;
import org.springframework.data.repository.CrudRepository;

public interface MangaPageCommentRepository extends CrudRepository<MangaPageCommentEntity, Long> {
}
