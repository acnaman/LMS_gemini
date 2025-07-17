package com.example.backend.domain.repository;

import com.example.backend.domain.model.LearningHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 学習履歴エンティティのためのリポジトリインターフェース。
 * Spring Data JPAによって提供されるCRUD操作を継承し、カスタムクエリを定義します。
 */
public interface LearningHistoryRepository extends JpaRepository<LearningHistory, Long> {
    /**
     * 指定されたユーザーIDに紐づく学習履歴のリストを検索します。
     *
     * @param userId ユーザーのID
     * @return 指定されたユーザーIDに紐づく学習履歴のリスト
     */
    List<LearningHistory> findByUserId(Long userId);
}
