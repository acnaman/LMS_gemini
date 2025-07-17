package com.example.backend.domain.repository;

import com.example.backend.domain.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 講座エンティティのためのリポジトリインターフェース。
 * Spring Data JPAによって提供されるCRUD操作を継承します。
 */
public interface CourseRepository extends JpaRepository<Course, Long> {
}
