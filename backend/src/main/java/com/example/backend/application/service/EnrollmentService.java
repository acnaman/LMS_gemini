package com.example.backend.application.service;

import com.example.backend.domain.model.Enrollment;
import com.example.backend.domain.repository.EnrollmentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 受講登録のアプリケーションサービス。
 * 受講登録の作成、取得機能を提供します。
 */
@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    /**
     * EnrollmentServiceの新しいインスタンスを構築します。
     *
     * @param enrollmentRepository 受講登録リポジトリ
     */
    public EnrollmentService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    /**
     * 新しい受講登録を作成します。
     *
     * @param enrollment 作成する受講登録エンティティ
     * @return 作成された受講登録エンティティ
     */
    public Enrollment createEnrollment(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    /**
     * 指定されたIDの受講登録を取得します。
     *
     * @param id 受講登録のID
     * @return 指定されたIDの受講登録（存在する場合）
     */
    public Optional<Enrollment> getEnrollmentById(Long id) {
        return enrollmentRepository.findById(id);
    }

    /**
     * すべての受講登録を取得します。
     *
     * @return すべての受講登録のリスト
     */
    public java.util.List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    }
