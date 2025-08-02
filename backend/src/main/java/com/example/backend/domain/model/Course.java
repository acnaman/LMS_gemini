package com.example.backend.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;

/**
 * 講座エンティティ。
 * 講座のID、タイトル、説明を管理します。
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Course {
    /**
     * 講座ID。
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
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
