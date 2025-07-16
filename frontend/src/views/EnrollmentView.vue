<template>
  <div>
    <h2>受講申込</h2>
    <h3>講座一覧</h3>
    <ul>
      <li v-for="course in availableCourses" :key="course.id">
        <strong>{{ course.title }}</strong>: {{ course.description }}
        <button @click="enroll(course.id)">申し込む</button>
      </li>
    </ul>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';

const availableCourses = ref([]);

const COURSES_API_URL = 'http://localhost:8080/api/admin/courses'; // 講座一覧API
const ENROLLMENTS_API_URL = 'http://localhost:8080/api/enrollments'; // 受講申込API

const fetchAvailableCourses = async () => {
  try {
    // 本来は全ユーザーがアクセスできる講座一覧APIを呼び出すべきだが、
    // 現状は管理者用のAPIを流用する
    const response = await axios.get(COURSES_API_URL);
    availableCourses.value = response.data;
  } catch (error) {
    console.error('受講可能講座の取得に失敗しました:', error);
  }
};

const enroll = async (courseId) => {
  try {
    // 実際のアプリケーションでは、ログインしているユーザーのIDを渡す必要がある
    const userId = 1; // 仮のユーザーID
    await axios.post(ENROLLMENTS_API_URL, { userId, courseId });
    alert(`講座ID: ${courseId} に申し込みました。`);
    // 申込後の状態更新など（例：申込済み講座はボタンを非表示にする）は後ほど実装
  } catch (error) {
    console.error('受講申込に失敗しました:', error);
    alert('受講申込に失敗しました。');
  }
};

onMounted(fetchAvailableCourses);
</script>

<style scoped>
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

li button {
  margin-left: var(--jps-spacing-md);
  padding: var(--jps-spacing-xs) var(--jps-spacing-sm);
}
</style>
