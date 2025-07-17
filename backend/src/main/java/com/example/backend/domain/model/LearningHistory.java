package com.example.backend.domain.model;

import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * 学習履歴エンティティ。
 * ユーザー、講座、完了日を管理します。
 */
@Entity
public class LearningHistory {
    /**
     * 学習履歴ID。
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 学習履歴に紐づくユーザー。
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * 学習履歴に紐づく講座。
     */
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    /**
     * 学習の完了日。
     */
    private LocalDate completionDate;

    /**
     * 新しい学習履歴を構築します。
     *
     * @param id 学習履歴ID
     * @param user ユーザー
     * @param course 講座
     * @param completionDate 完了日
     */
    public LearningHistory(Long id, User user, Course course, LocalDate completionDate) {
        this.id = id;
        this.user = user;
        this.course = course;
        this.completionDate = completionDate;
    }

    /**
     * JPAのために必要なデフォルトコンストラクタ。
     */
    protected LearningHistory() {
    }

    /**
     * 学習履歴IDを取得します。
     *
     * @return 学習履歴ID
     */
    public Long getId() {
        return id;
    }

    /**
     * ユーザーを取得します。
     *
     * @return ユーザー
     */
    public User getUser() {
        return user;
    }

    /**
     * 講座を取得します。
     *
     * @return 講座
     */
    public Course getCourse() {
        return course;
    }

    /**
     * 完了日を取得します。
     *
     * @return 完了日
     */
    public LocalDate getCompletionDate() {
        return completionDate;
    }
}
