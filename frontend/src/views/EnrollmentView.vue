<template>
  <div>
    <h1 class="mb-4">受講申込</h1>

    <div class="card">
      <div class="card-header">
        開講中のコース
      </div>
      <div class="card-body">
        <table class="table table-striped table-hover">
          <thead>
            <tr>
              <th scope="col">タイトル</th>
              <th scope="col">説明</th>
              <th scope="col">操作</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="course in availableCourses" :key="course.id">
              <td>{{ course.title }}</td>
              <td>{{ course.description }}</td>
              <td>
                <button type="button" class="btn btn-sm btn-primary" @click="enroll(course.id)">申し込む</button>
              </td>
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

const availableCourses = ref([]);

const COURSES_API_URL = 'http://localhost:8080/api/admin/courses';
const ENROLLMENTS_API_URL = 'http://localhost:8080/api/enrollments';

const fetchAvailableCourses = async () => {
  try {
    const response = await axios.get(COURSES_API_URL);
    availableCourses.value = response.data;
  } catch (error) {
    console.error('受講可能講座の取得に失敗しました:', error);
  }
};

const enroll = async (courseId) => {
  try {
    const userId = 1; // 仮のユーザーID
    await axios.post(ENROLLMENTS_API_URL, { userId, courseId });
    alert(`講座ID: ${courseId} に申し込みました。`);
  } catch (error) {
    console.error('受講申込に失敗しました:', error);
    alert('受講申込に失敗しました。');
  }
};

onMounted(fetchAvailableCourses);
</script>

<style scoped>
</style>
