<template>
  <div>
    <h1 class="mb-4">コース管理</h1>

    <!-- アクションエリア -->
    <div class="d-flex justify-content-between mb-3">
      <form class="d-flex" role="search">
        <input class="form-control me-2" type="search" placeholder="コース名で検索..." aria-label="Search">
        <button class="btn btn-outline-secondary" type="submit">検索</button>
      </form>
      <button type="button" class="btn btn-primary" @click="showCreateModal = true">
        新規コース追加
      </button>
    </div>

    <!-- コース一覧テーブル -->
    <div class="card">
      <div class="card-header">
        コース一覧
      </div>
      <div class="card-body">
        <table class="table table-striped table-hover">
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">タイトル</th>
              <th scope="col">説明</th>
              <th scope="col">操作</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="course in courses" :key="course.id">
              <th scope="row">{{ course.id }}</th>
              <td>{{ course.title }}</td>
              <td>{{ course.description }}</td>
              <td>
                <button type="button" class="btn btn-sm btn-outline-primary me-1" @click="editCourse(course)">編集</button>
                <button type="button" class="btn btn-sm btn-outline-danger" @click="deleteCourse(course.id)">削除</button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>

    <!-- 新規・編集モーダル -->
    <div v-if="showCreateModal || editingCourse" class="modal fade show d-block" tabindex="-1">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">{{ editingCourse ? 'コース編集' : '新規コース追加' }}</h5>
            <button type="button" class="btn-close" @click="cancelEdit"></button>
          </div>
          <div class="modal-body">
            <form @submit.prevent="editingCourse ? updateCourse() : createCourse()">
              <div class="mb-3">
                <label for="title" class="form-label">タイトル</label>
                <input type="text" class="form-control" id="title" v-model="currentCourse.title" required>
              </div>
              <div class="mb-3">
                <label for="description" class="form-label">説明</label>
                <textarea class="form-control" id="description" v-model="currentCourse.description" rows="3"></textarea>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" @click="cancelEdit">閉じる</button>
            <button type="button" class="btn btn-primary" @click="editingCourse ? updateCourse() : createCourse()">保存</button>
          </div>
        </div>
      </div>
    </div>
    <div v-if="showCreateModal || editingCourse" class="modal-backdrop fade show"></div>

  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';

const courses = ref([]);
const showCreateModal = ref(false);
const editingCourse = ref(null);
const currentCourse = ref({ title: '', description: '' });

const API_BASE_URL = 'http://localhost:8080/api/admin/courses';

const fetchCourses = async () => {
  try {
    const response = await axios.get(API_BASE_URL);
    courses.value = response.data;
  } catch (error) {
    console.error('コースの取得に失敗しました:', error);
  }
};

const createCourse = async () => {
  try {
    const response = await axios.post(API_BASE_URL, currentCourse.value);
    courses.value.push(response.data);
    cancelEdit();
  } catch (error) {
    console.error('コースの登録に失敗しました:', error);
  }
};

const editCourse = (course) => {
  editingCourse.value = { ...course };
  currentCourse.value = { ...course };
};

const updateCourse = async () => {
  try {
    const response = await axios.put(`${API_BASE_URL}/${editingCourse.value.id}`, currentCourse.value);
    const index = courses.value.findIndex(c => c.id === response.data.id);
    if (index !== -1) {
      courses.value[index] = response.data;
    }
    cancelEdit();
  } catch (error) {
    console.error('コースの更新に失敗しました:', error);
  }
};

const deleteCourse = async (id) => {
  if (!confirm('本当にこのコースを削除しますか？')) return;
  try {
    await axios.delete(`${API_BASE_URL}/${id}`);
    courses.value = courses.value.filter(course => course.id !== id);
  } catch (error) {
    console.error('コースの削除に失敗しました:', error);
  }
};

const cancelEdit = () => {
  showCreateModal.value = false;
  editingCourse.value = null;
  currentCourse.value = { title: '', description: '' };
};

onMounted(fetchCourses);
</script>

<style scoped>
.d-block {
    display: block;
}
</style>
