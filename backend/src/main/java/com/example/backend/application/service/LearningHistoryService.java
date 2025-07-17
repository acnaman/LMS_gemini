package com.example.backend.application.service;

import com.example.backend.domain.model.LearningHistory;
import com.example.backend.domain.repository.LearningHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 学習履歴のアプリケーションサービス。
 * 学習履歴の作成、取得、ユーザーIDによる検索機能を提供します。
 */
@Service
public class LearningHistoryService {

    private final LearningHistoryRepository learningHistoryRepository;

    /**
     * LearningHistoryServiceの新しいインスタンスを構築します。
     *
     * @param learningHistoryRepository 学習履歴リポジトリ
     */
    public LearningHistoryService(LearningHistoryRepository learningHistoryRepository) {
        this.learningHistoryRepository = learningHistoryRepository;
    }

    /**
     * 新しい学習履歴を作成します。
     *
     * @param learningHistory 作成する学習履歴エンティティ
     * @return 作成された学習履歴エンティティ
     */
    public LearningHistory createLearningHistory(LearningHistory learningHistory) {
        return learningHistoryRepository.save(learningHistory);
    }

    /**
     * 指定されたIDの学習履歴を取得します。
     *
     * @param id 学習履歴のID
     * @return 指定されたIDの学習履歴（存在する場合）
     */
    public Optional<LearningHistory> getLearningHistoryById(Long id) {
        return learningHistoryRepository.findById(id);
    }

    /**
     * 指定されたユーザーIDの学習履歴をすべて取得します。
     *
     * @param userId ユーザーのID
     * @return 指定されたユーザーIDの学習履歴のリスト
     */
    public List<LearningHistory> getLearningHistoriesByUserId(Long userId) {
        return learningHistoryRepository.findByUserId(userId);
    }
}
