package com.example.backend.application.service;

import com.example.backend.domain.model.Course;
import com.example.backend.domain.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 講座のアプリケーションサービス。
 * 講座の作成、取得、更新、削除機能を提供します。
 */
@Service
public class CourseService {

    private final CourseRepository courseRepository;

    /**
     * CourseServiceの新しいインスタンスを構築します。
     *
     * @param courseRepository 講座リポジトリ
     */
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    /**
     * 新しい講座を作成します。
     *
     * @param course 作成する講座エンティティ
     * @return 作成された講座エンティティ
     */
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    /**
     * 指定されたIDの講座を取得します。
     *
     * @param id 講座のID
     * @return 指定されたIDの講座（存在する場合）
     */
    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    /**
     * 講座を更新します。
     *
     * @param course 更新する講座エンティティ
     * @return 更新された講座エンティティ
     */
    public Course updateCourse(Course course) {
        return courseRepository.save(course);
    }

    /**
     * 指定されたIDの講座を削除します。
     *
     * @param id 削除する講座のID
     */
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}
