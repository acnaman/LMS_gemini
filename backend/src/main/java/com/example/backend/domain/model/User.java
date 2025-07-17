package com.example.backend.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * ユーザーエンティティ。
 * ユーザーのID、ユーザー名、役割を管理します。
 */
@Entity(name = "app_user")
public class User {
    /**
     * ユーザーID。
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * ユーザー名。
     */
    private String username;

    /**
     * ユーザーの役割。
     */
    private Role role;

    /**
     * 新しいユーザーを構築します。
     *
     * @param id ユーザーID
     * @param username ユーザー名
     * @param role ユーザーの役割
     */
    public User(Long id, String username, Role role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

    /**
     * JPAのために必要なデフォルトコンストラクタ。
     */
    protected User() {
    }

    /**
     * ユーザーIDを取得します。
     *
     * @return ユーザーID
     */
    public Long getId() {
        return id;
    }

    /**
     * ユーザー名を取得します。
     *
     * @return ユーザー名
     */
    public String getUsername() {
        return username;
    }

    /**
     * ユーザーの役割を取得します。
     *
     * @return ユーザーの役割
     */
    public Role getRole() {
        return role;
    }
}
