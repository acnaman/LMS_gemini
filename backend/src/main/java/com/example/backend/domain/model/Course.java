package com.example.backend.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * 講座エンティティ。
 * 講座のID、タイトル、説明を管理します。
 */
@Entity
public class Course {
    /**
     * 講座ID。
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 講座のタイトル。
     */
    private String title;

    /**
     * 講座の説明。
     */
    private String description;

    /**
     * 新しい講座を構築します。
     *
     * @param id 講座ID
     * @param title 講座のタイトル
     * @param description 講座の説明
     */
    public Course(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    /**
     * JPAのために必要なデフォルトコンストラクタ。
     */
    protected Course() {
    }

    /**
     * 講座IDを取得します。
     *
     * @return 講座ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 講座のタイトルを取得します。
     *
     * @return 講座のタイトル
     */
    public String getTitle() {
        return title;
    }

    /**
     * 講座の説明を取得します。
     *
     * @return 講座の説明
     */
    public String getDescription() {
        return description;
    }
}
