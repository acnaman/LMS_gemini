<template>
  <div>
    <h2>ユーザー管理</h2>

    <h3>新規ユーザー登録</h3>
    <form @submit.prevent="createUser">
      <input type="text" v-model="newUser.username" placeholder="ユーザー名" required />
      <select v-model="newUser.role">
        <option value="USER">一般ユーザー</option>
        <option value="ADMIN">管理者</option>
      </select>
      <button type="submit">登録</button>
    </form>

    <h3>ユーザー一覧</h3>
    <ul>
      <li v-for="user in users" :key="user.id">
        {{ user.username }} ({{ user.role }})
        <button @click="editUser(user)">編集</button>
        <button @click="deleteUser(user.id)">削除</button>
      </li>
    </ul>

    <h3 v-if="editingUser">ユーザー編集</h3>
    <form v-if="editingUser" @submit.prevent="updateUser">
      <input type="text" v-model="editingUser.username" placeholder="ユーザー名" required />
      <select v-model="editingUser.role">
        <option value="USER">一般ユーザー</option>
        <option value="ADMIN">管理者</option>
      </select>
      <button type="submit">更新</button>
      <button @click="cancelEdit">キャンセル</button>
    </form>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';

const users = ref([]);
const newUser = ref({ username: '', role: 'USER' });
const editingUser = ref(null);

const API_BASE_URL = 'http://localhost:8080/api/admin/users'; // バックエンドAPIのURL

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
    const response = await axios.post(API_BASE_URL, newUser.value);
    users.value.push(response.data);
    newUser.value = { username: '', role: 'USER' };
  } catch (error) {
    console.error('ユーザーの登録に失敗しました:', error);
  }
};

const editUser = (user) => {
  editingUser.value = { ...user };
};

const updateUser = async () => {
  try {
    const response = await axios.put(`${API_BASE_URL}/${editingUser.value.id}`, editingUser.value);
    const index = users.value.findIndex(u => u.id === response.data.id);
    if (index !== -1) {
      users.value[index] = response.data;
    }
    editingUser.value = null;
  } catch (error) {
    console.error('ユーザーの更新に失敗しました:', error);
  }
};

const deleteUser = async (id) => {
  try {
    await axios.delete(`${API_BASE_URL}/${id}`);
    users.value = users.value.filter(user => user.id !== id);
  } catch (error) {
    console.error('ユーザーの削除に失敗しました:', error);
  }
};

const cancelEdit = () => {
  editingUser.value = null;
};

onMounted(fetchUsers);
</script>

<style scoped>
form {
  margin-bottom: var(--jps-spacing-lg);
}

form input, form select, form button {
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
