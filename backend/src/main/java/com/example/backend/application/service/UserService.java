package com.example.backend.application.service;

import com.example.backend.domain.model.User;
import com.example.backend.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * ユーザーのアプリケーションサービス。
 * ユーザーの作成、取得、更新、削除機能を提供します。
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    /**
     * UserServiceの新しいインスタンスを構築します。
     *
     * @param userRepository ユーザーリポジトリ
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 新しいユーザーを作成します。
     *
     * @param user 作成するユーザーエンティティ
     * @return 作成されたユーザーエンティティ
     */
    public User createUser(User user) {
        return userRepository.save(user);
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
     * ユーザーを更新します。
     *
     * @param user 更新するユーザーエンティティ
     * @return 更新されたユーザーエンティティ
     */
    public User updateUser(User user) {
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
