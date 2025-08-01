INSERT INTO app_user (id, username, role) VALUES (1, 'adminuser', 0);
INSERT INTO app_user (id, username, role) VALUES (2, 'generaluser', 1);

-- 講座のサンプルデータ
INSERT INTO course (id, title, description) VALUES (1, 'Javaプログラミング基礎', 'Javaの基本的な文法やオブジェクト指向プログラミングの概念を学びます。');
INSERT INTO course (id, title, description) VALUES (2, 'Spring Boot入門', 'Spring Bootを使ったWebアプリケーション開発の基礎を学びます。');
INSERT INTO course (id, title, description) VALUES (3, 'データベース設計', 'リレーショナルデータベースの設計原則や正規化について学びます。');

-- 受講登録のサンプルデータ
INSERT INTO enrollment (user_id, course_id) VALUES (1, 1);
INSERT INTO enrollment (user_id, course_id) VALUES (1, 2);
INSERT INTO enrollment (user_id, course_id) VALUES (2, 1);

-- 学習履歴のサンプルデータ
INSERT INTO learning_history (user_id, course_id, completion_date) VALUES (1, 1, '2024-07-01');
