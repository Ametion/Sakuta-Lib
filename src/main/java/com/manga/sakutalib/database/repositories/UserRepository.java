package com.manga.sakutalib.database.repositories;

import com.manga.sakutalib.database.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByLogin(String login);
}