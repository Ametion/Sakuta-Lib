package com.manga.sakutalib.database.repositories;

import com.manga.sakutalib.database.entities.VolumeEntity;
import org.springframework.data.repository.CrudRepository;

public interface VolumeRepository extends CrudRepository<VolumeEntity, Long> { }