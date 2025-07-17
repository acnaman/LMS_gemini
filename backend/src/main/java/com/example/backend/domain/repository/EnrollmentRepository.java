package com.example.backend.domain.repository;

import com.example.backend.domain.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 受講登録エンティティのためのリポジトリインターフェース。
 * Spring Data JPAによって提供されるCRUD操作を継承します。
 */
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
}
