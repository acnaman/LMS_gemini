<template>
  <div>
    <h1 class="mb-4">ユーザー管理</h1>

    <!-- アクションエリア -->
    <div class="d-flex justify-content-between mb-3">
      <form class="d-flex" role="search">
        <input class="form-control me-2" type="search" placeholder="ユーザー名で検索..." aria-label="Search">
        <button class="btn btn-outline-secondary" type="submit">検索</button>
      </form>
      <button type="button" class="btn btn-primary" @click="showCreateModal = true">
        新規ユーザー追加
      </button>
    </div>

    <!-- ユーザー一覧テーブル -->
    <div class="card">
      <div class="card-header">
        ユーザー一覧
      </div>
      <div class="card-body">
        <table class="table table-striped table-hover">
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">名前</th>
              <th scope="col">ロール</th>
              <th scope="col">操作</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="user in users" :key="user.id">
              <th scope="row">{{ user.id }}</th>
              <td>{{ user.username }}</td>
              <td>{{ user.role }}</td>
              <td>
                <button type="button" class="btn btn-sm btn-outline-primary me-1" @click="editUser(user)">編集</button>
                <button type="button" class="btn btn-sm btn-outline-danger" @click="deleteUser(user.id)">削除</button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>

    <!-- 新規・編集モーダル -->
    <div v-if="showCreateModal || editingUser" class="modal fade show d-block" tabindex="-1">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">{{ editingUser ? 'ユーザー編集' : '新規ユーザー追加' }}</h5>
            <button type="button" class="btn-close" @click="cancelEdit"></button>
          </div>
          <div class="modal-body">
            <form @submit.prevent="editingUser ? updateUser() : createUser()">
              <div class="mb-3">
                <label for="username" class="form-label">ユーザー名</label>
                <input type="text" class="form-control" id="username" v-model="currentUser.username" required>
              </div>
              <div class="mb-3">
                <label for="role" class="form-label">ロール</label>
                <select class="form-select" id="role" v-model="currentUser.role">
                  <option value="USER">一般ユーザー</option>
                  <option value="ADMIN">管理者</option>
                </select>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" @click="cancelEdit">閉じる</button>
            <button type="button" class="btn btn-primary" @click="editingUser ? updateUser() : createUser()">保存</button>
          </div>
        </div>
      </div>
    </div>
    <div v-if="showCreateModal || editingUser" class="modal-backdrop fade show"></div>

  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';

const users = ref([]);
const showCreateModal = ref(false);
const editingUser = ref(null);
const currentUser = ref({ username: '', role: 'USER' });

const API_BASE_URL = 'http://localhost:8080/api/admin/users';

const fetchUsers = async () => {
  try {
    const response = await axios.get(API_BASE_URL);
    users.value = response.data;
  } catch (error) {
    console.error('ユーザーの取得に失敗しました:', error);
  }
};

const createUser = async () => {
  try {
    const response = await axios.post(API_BASE_URL, currentUser.value);
    users.value.push(response.data);
    cancelEdit();
  } catch (error) {
    console.error('ユーザーの登録に失敗しました:', error);
  }
};

const editUser = (user) => {
  editingUser.value = { ...user };
  currentUser.value = { ...user };
};

const updateUser = async () => {
  try {
    const response = await axios.put(`${API_BASE_URL}/${editingUser.value.id}`, currentUser.value);
    const index = users.value.findIndex(u => u.id === response.data.id);
    if (index !== -1) {
      users.value[index] = response.data;
    }
    cancelEdit();
  } catch (error) {
    console.error('ユーザーの更新に失敗しました:', error);
  }
};

const deleteUser = async (id) => {
  if (!confirm('本当にこのユーザーを削除しますか？')) return;
  try {
    await axios.delete(`${API_BASE_URL}/${id}`);
    users.value = users.value.filter(user => user.id !== id);
  } catch (error) {
    console.error('ユーザーの削除に失敗しました:', error);
  }
};

const cancelEdit = () => {
  showCreateModal.value = false;
  editingUser.value = null;
  currentUser.value = { username: '', role: 'USER' };
};

onMounted(fetchUsers);
</script>

<style scoped>
.d-block {
    display: block;
}
</style>
