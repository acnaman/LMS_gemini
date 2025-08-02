package com.example.backend.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore; // Import JsonIgnore
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;

/**
 * ユーザーエンティティ。
 * ユーザーのID、ユーザー名、役割を管理します。
 */
@Entity(name = "app_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {
    /**
     * ユーザーID。
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    /**
     * ユーザー名。
     */
    private String username;

    /**
     * パスワード。
     */
    @JsonIgnore // Add this annotation
    private String password;

    /**
     * ユーザーの役割。
     */
    private Role role;

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
     * パスワードを取得します。
     *
     * @return パスワード
     */
    public String getPassword() {
        return password;
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
