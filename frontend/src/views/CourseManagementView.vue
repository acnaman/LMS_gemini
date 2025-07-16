<template>
  <div>
    <h2>講座管理</h2>

    <h3>新規講座登録</h3>
    <form @submit.prevent="createCourse">
      <input type="text" v-model="newCourse.title" placeholder="講座タイトル" required />
      <input type="text" v-model="newCourse.description" placeholder="講座説明" required />
      <button type="submit">登録</button>
    </form>

    <h3>講座一覧</h3>
    <ul>
      <li v-for="course in courses" :key="course.id">
        <strong>{{ course.title }}</strong>: {{ course.description }}
        <div>
          <button @click="editCourse(course)">編集</button>
          <button @click="deleteCourse(course.id)">削除</button>
        </div>
      </li>
    </ul>

    <h3 v-if="editingCourse">講座編集</h3>
    <form v-if="editingCourse" @submit.prevent="updateCourse">
      <input type="text" v-model="editingCourse.title" placeholder="講座タイトル" required />
      <input type="text" v-model="editingCourse.description" placeholder="講座説明" required />
      <button type="submit">更新</button>
      <button @click="cancelEdit">キャンセル</button>
    </form>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';

const courses = ref([]);
const newCourse = ref({ title: '', description: '' });
const editingCourse = ref(null);

const API_BASE_URL = 'http://localhost:8080/api/admin/courses'; // バックエンドAPIのURL

const fetchCourses = async () => {
  try {
    const response = await axios.get(API_BASE_URL);
    courses.value = response.data;
  } catch (error) {
    console.error('講座の取得に失敗しました:', error);
  }
};

const createCourse = async () => {
  try {
    const response = await axios.post(API_BASE_URL, newCourse.value);
    courses.value.push(response.data);
    newCourse.value = { title: '', description: '' };
  } catch (error) {
    console.error('講座の登録に失敗しました:', error);
  }
};

const editCourse = (course) => {
  editingCourse.value = { ...course };
};

const updateCourse = async () => {
  try {
    const response = await axios.put(`${API_BASE_URL}/${editingCourse.value.id}`, editingCourse.value);
    const index = courses.value.findIndex(c => c.id === response.data.id);
    if (index !== -1) {
      courses.value[index] = response.data;
    }
    editingCourse.value = null;
  } catch (error) {
    console.error('講座の更新に失敗しました:', error);
  }
};

const deleteCourse = async (id) => {
  try {
    await axios.delete(`${API_BASE_URL}/${id}`);
    courses.value = courses.value.filter(course => course.id !== id);
  } catch (error) {
    console.error('講座の削除に失敗しました:', error);
  }
};

const cancelEdit = () => {
  editingCourse.value = null;
};

onMounted(fetchCourses);
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

li button {
  margin-left: var(--jps-spacing-md);
  padding: var(--jps-spacing-xs) var(--jps-spacing-sm);
}
</style>
