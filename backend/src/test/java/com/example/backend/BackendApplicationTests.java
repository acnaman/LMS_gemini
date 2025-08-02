package com.example.backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest
@WithMockUser // テスト時に認証済みのユーザーとして実行
class BackendApplicationTests {

	@Test
	void contextLoads() {
	}

}
