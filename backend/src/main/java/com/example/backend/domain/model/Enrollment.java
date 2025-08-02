package com.example.backend.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;

/**
 * 受講登録エンティティ。
 * 受講登録のID、ユーザー、講座を管理します。
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Enrollment {
    /**
     * 受講登録ID。
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
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
