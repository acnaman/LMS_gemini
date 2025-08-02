package com.example.backend.application.service;

import com.example.backend.domain.model.User;
import com.example.backend.domain.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * ユーザーのアプリケーションサービス。
 * ユーザーの作成、取得、更新、削除機能を提供します。
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * UserServiceの新しいインスタンスを構築します。
     *
     * @param userRepository ユーザーリポジトリ
     * @param passwordEncoder パスワードエンコーダー
     */
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 新しいユーザーを作成します。
     *
     * @param user 作成するユーザーエンティティ
     * @return 作成されたユーザーエンティティ
     */
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    /**
     * ユーザー名でユーザーを取得します。
     *
     * @param username ユーザー名
     * @return 指定されたユーザー名のユーザー（存在する場合）
     */
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * 指定されたIDのユーザーを取得します。
     *
     * @param id ユーザーのID
     * @return 指定されたIDのユーザー（存在する場合）
     */
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * すべてのユーザーを取得します。
     *
     * @return すべてのユーザーのリスト
     */
    public java.util.List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * ユーザーを更新します。
     *
     * @param user 更新するユーザーエンティティ
     * @return 更新されたユーザーエンティティ
     */
    public User updateUser(User user) {
        // パスワードが更新される場合はハッシュ化する
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return userRepository.save(user);
    }

    /**
     * 指定されたIDのユーザーを削除します。
     *
     * @param id 削除するユーザーのID
     */
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
