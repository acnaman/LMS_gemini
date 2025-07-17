<template>
  <div>
    <h1 class="mb-4">学習履歴</h1>

    <div class="card">
      <div class="card-header">
        受講済みコース
      </div>
      <div class="card-body">
        <table class="table table-striped table-hover">
          <thead>
            <tr>
              <th scope="col">コース名</th>
              <th scope="col">完了日</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="history in learningHistories" :key="history.id">
              <td>{{ history.course.title }}</td>
              <td>{{ history.completionDate }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';

const learningHistories = ref([]);

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

onMounted(fetchLearningHistories);
</script>

<style scoped>
</style>
