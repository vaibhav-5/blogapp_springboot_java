package com.blog.app.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UsersRepository extends JpaRepository<UserEntity, Long> {

	Optional<UserEntity> findByUserName(String userName);
}
