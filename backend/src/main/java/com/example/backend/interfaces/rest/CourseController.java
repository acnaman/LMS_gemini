package com.example.backend.interfaces.rest;

import com.example.backend.application.service.CourseService;
import com.example.backend.domain.model.Course;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * 講座管理のためのRESTコントローラー。
 * 管理者権限を持つユーザーが講座の作成、取得、更新、削除を行うためのAPIを提供します。
 */
@RestController
@RequestMapping("/api/admin/courses")
public class CourseController {

    private final CourseService courseService;

    /**
     * CourseControllerの新しいインスタンスを構築します。
     *
     * @param courseService 講座サービス
     */
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    /**
     * 新しい講座を作成します。
     *
     * @param course 作成する講座エンティティ
     * @return 作成された講座エンティティ
     */
    @PostMapping
    public Course createCourse(@RequestBody Course course) {
        return courseService.createCourse(course);
    }

    /**
     * 指定されたIDの講座を取得します。
     *
     * @param id 講座のID
     * @return 指定されたIDの講座（存在する場合）
     */
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        Optional<Course> course = courseService.getCourseById(id);
        return course.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * 講座を更新します。
     *
     * @param id 講座のID
     * @param course 更新する講座エンティティ
     * @return 更新された講座エンティティ
     */
    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable Long id, @RequestBody Course course) {
        // For simplicity, we'll assume the ID in the path matches the ID in the request body
        return courseService.updateCourse(course);
    }

    /**
     * 指定されたIDの講座を削除します。
     *
     * @param id 削除する講座のID
     * @return HTTP 204 No Content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}
