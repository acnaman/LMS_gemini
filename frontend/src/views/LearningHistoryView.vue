<template>
  <div>
    <h2>受講履歴</h2>

    <h3>手動登録</h3>
    <form @submit.prevent="addHistory">
      <input type="text" v-model="manualHistory.courseName" placeholder="講座名" required />
      <input type="date" v-model="manualHistory.completionDate" required />
      <button type="submit">登録</button>
    </form>

    <h3>受講履歴一覧</h3>
    <ul>
      <li v-for="history in learningHistories" :key="history.id">
        <strong>{{ history.course.title }}</strong> ({{ history.completionDate }})
      </li>
    </ul>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';

const learningHistories = ref([]);
const manualHistory = ref({ courseName: '', completionDate: '' });

// 実際のアプリケーションでは、ログインしているユーザーのIDを動的に取得する
const MOCK_USER_ID = 1;
const API_BASE_URL = `http://localhost:8080/api/learning-histories`;

const fetchLearningHistories = async () => {
  try {
    const response = await axios.get(`${API_BASE_URL}/user/${MOCK_USER_ID}`);
    learningHistories.value = response.data;
  } catch (error) {
    console.error('受講履歴の取得に失敗しました:', error);
  }
};

const addHistory = async () => {
  try {
    // 手動登録用のAPIを呼び出す
    // このAPIはまだ実装されていないため、これはプレースホルダーです
    const newHistory = {
      userId: MOCK_USER_ID,
      // 手動登録の場合、Courseエンティティをどう扱うか設計が必要
      // ここでは簡略化のため、講座名を直接渡す想定
      course: { title: manualHistory.value.courseName, description: '手動登録' },
      completionDate: manualHistory.value.completionDate,
    };

    const response = await axios.post(API_BASE_URL, newHistory);
    learningHistories.value.push(response.data);
    manualHistory.value = { courseName: '', completionDate: '' };
    alert('受講履歴を手動で登録しました。');
  } catch (error) {
    console.error('受講履歴の手動登録に失敗しました:', error);
    alert('受講履歴の手動登録に失敗しました。');
  }
};

onMounted(fetchLearningHistories);
</script>

<style scoped>
form {
  margin-bottom: var(--jps-spacing-lg);
}

form input, form button {
  margin-right: var(--jps-spacing-md);
  padding: var(--jps-spacing-sm);
}

ul {
  list-style: none;
  padding: 0;
}

li {
  background-color: var(--jps-color-background-secondary);
  border: 1px solid var(--jps-color-border-divider);
  padding: var(--jps-spacing-md);
  margin-bottom: var(--jps-spacing-xs);
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style>
