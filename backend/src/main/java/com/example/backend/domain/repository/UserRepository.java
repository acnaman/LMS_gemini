package com.example.backend.domain.repository;

import com.example.backend.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ユーザーエンティティのためのリポジトリインターフェース。
 * Spring Data JPAによって提供されるCRUD操作を継承します。
 */
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
