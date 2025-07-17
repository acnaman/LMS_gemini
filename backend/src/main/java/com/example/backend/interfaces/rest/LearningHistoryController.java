package com.example.backend.interfaces.rest;

import com.example.backend.application.service.LearningHistoryService;
import com.example.backend.domain.model.LearningHistory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * 学習履歴のためのRESTコントローラー。
 * 学習履歴の作成、取得、ユーザーIDによる検索を行うためのAPIを提供します。
 */
@RestController
@RequestMapping("/api/learning-histories")
public class LearningHistoryController {

    private final LearningHistoryService learningHistoryService;

    /**
     * LearningHistoryControllerの新しいインスタンスを構築します。
     *
     * @param learningHistoryService 学習履歴サービス
     */
    public LearningHistoryController(LearningHistoryService learningHistoryService) {
        this.learningHistoryService = learningHistoryService;
    }

    /**
     * 新しい学習履歴を作成します。
     *
     * @param learningHistory 作成する学習履歴エンティティ
     * @return 作成された学習履歴エンティティ
     */
    @PostMapping
    public LearningHistory createLearningHistory(@RequestBody LearningHistory learningHistory) {
        return learningHistoryService.createLearningHistory(learningHistory);
    }

    /**
     * 指定されたIDの学習履歴を取得します。
     *
     * @param id 学習履歴のID
     * @return 指定されたIDの学習履歴（存在する場合）
     */
    @GetMapping("/{id}")
    public ResponseEntity<LearningHistory> getLearningHistoryById(@PathVariable Long id) {
        Optional<LearningHistory> learningHistory = learningHistoryService.getLearningHistoryById(id);
        return learningHistory.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * 指定されたユーザーIDの学習履歴をすべて取得します。
     *
     * @param userId ユーザーのID
     * @return 指定されたユーザーIDの学習履歴のリスト
     */
    @GetMapping("/user/{userId}")
    public List<LearningHistory> getLearningHistoriesByUserId(@PathVariable Long userId) {
        return learningHistoryService.getLearningHistoriesByUserId(userId);
    }
}
