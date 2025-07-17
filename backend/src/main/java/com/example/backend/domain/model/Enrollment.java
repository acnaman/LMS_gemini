package com.example.backend.domain.model;

import jakarta.persistence.*;

/**
 * 受講登録エンティティ。
 * 受講登録のID、ユーザー、講座を管理します。
 */
@Entity
public class Enrollment {
    /**
     * 受講登録ID。
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 受講登録に紐づくユーザー。
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * 受講登録に紐づく講座。
     */
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    /**
     * 新しい受講登録を構築します。
     *
     * @param id 受講登録ID
     * @param user ユーザー
     * @param course 講座
     */
    public Enrollment(Long id, User user, Course course) {
        this.id = id;
        this.user = user;
        this.course = course;
    }

    /**
     * JPAのために必要なデフォルトコンストラクタ。
     */
    protected Enrollment() {
    }

    /**
     * 受講登録IDを取得します。
     *
     * @return 受講登録ID
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
}
