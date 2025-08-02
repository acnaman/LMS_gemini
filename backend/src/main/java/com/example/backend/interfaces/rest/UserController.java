package com.example.backend.interfaces.rest;

import com.example.backend.application.service.UserService;
import com.example.backend.domain.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping; // Add this import
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * ユーザー管理のためのRESTコントローラー。
 * 管理者権限を持つユーザーがユーザーの作成、取得、更新、削除を行うためのAPIを提供します。
 */
@RestController
@RequestMapping("/api/admin/users")
public class UserController {

    private final UserService userService;

    /**
     * UserControllerの新しいインスタンスを構築します。
     *
     * @param userService ユーザーサービス
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 新しいユーザーを作成します。
     *
     * @param user 作成するユーザーエンティティ
     * @return 作成されたユーザーエンティティ
     */
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.status(201).body(createdUser);
    }

    /**
     * 指定されたIDのユーザーを取得します。
     *
     * @param id ユーザーのID
     * @return 指定されたIDのユーザー（存在する場合）
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * すべてのユーザーを取得します。
     *
     * @return すべてのユーザーのリスト
     */
    @GetMapping
    public java.util.List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * ユーザーを更新します。
     *
     * @param id ユーザーのID
     * @param user 更新するユーザーエンティティ
     * @return 更新されたユーザーエンティティ
     */
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        // Ensure the ID in the path matches the ID in the request body
        // For simplicity, we'll assume they match or use the path ID
        return userService.updateUser(user);
    }

    /**
     * 指定されたIDのユーザーを削除します。
     *
     * @param id 削除するユーザーのID
     * @return HTTP 204 No Content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
