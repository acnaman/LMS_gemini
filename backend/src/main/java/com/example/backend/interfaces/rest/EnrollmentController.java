package com.example.backend.interfaces.rest;

import com.example.backend.application.service.EnrollmentService;
import com.example.backend.domain.model.Enrollment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * 受講登録のためのRESTコントローラー。
 * 受講登録の作成、取得を行うためのAPIを提供します。
 */
@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    /**
     * EnrollmentControllerの新しいインスタンスを構築します。
     *
     * @param enrollmentService 受講登録サービス
     */
    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    /**
     * 新しい受講登録を作成します。
     *
     * @param enrollment 作成する受講登録エンティティ
     * @return 作成された受講登録エンティティ
     */
    @PostMapping
    public ResponseEntity<Enrollment> createEnrollment(@RequestBody Enrollment enrollment) {
        Enrollment createdEnrollment = enrollmentService.createEnrollment(enrollment);
        return ResponseEntity.status(201).body(createdEnrollment);
    }

    /**
     * 指定されたIDの受講登録を取得します。
     *
     * @param id 受講登録のID
     * @return 指定されたIDの受講登録（存在する場合）
     */
    @GetMapping("/{id}")
    public ResponseEntity<Enrollment> getEnrollmentById(@PathVariable Long id) {
        Optional<Enrollment> enrollment = enrollmentService.getEnrollmentById(id);
        return enrollment.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * すべての受講登録を取得します。
     *
     * @return すべての受講登録のリスト
     */
    @GetMapping
    public java.util.List<Enrollment> getAllEnrollments() {
        return enrollmentService.getAllEnrollments();
    }
}
