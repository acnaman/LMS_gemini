<template>
  <div>
    <h1 class="mb-4">受講申込</h1>

    <div class="row row-cols-1 row-cols-md-2 row-cols-lg-2 g-4">
      <div class="col" v-for="course in availableCourses" :key="course.id">
        <div class="card h-100 d-flex flex-column">
          <div class="card-body flex-grow-1">
            <h5 class="card-title">{{ course.title }}</h5>
            <p class="card-text">{{ course.description }}</p>
            <!-- 講師情報があればここに追加 -->
            <!-- <p class="card-text"><small class="text-muted">講師: {{ course.instructor }}</small></p> -->
          </div>
          <div class="card-footer text-end">
            <button type="button" class="btn btn-sm btn-primary" @click="enroll(course.id)">申し込む</button>
          </div>
        </div>
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
.card {
  min-height: 180px; /* 適切な最小高さを設定 */
}
</style>