package com.manga.sakutalib.database.repositories;

import com.manga.sakutalib.database.entities.VolumeEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface VolumeRepository extends CrudRepository<VolumeEntity, Long> {
    Collection<VolumeEntity> findAllByManga_Id(Long id);
}