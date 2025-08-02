import '@digital-go-jp/design-tokens/dist/tokens.css'; // Corrected path
import 'bootstrap/dist/css/bootstrap.min.css';

import { createApp } from 'vue'
import router from './router'; // 新しく作成したルーターをインポート
import App from './App.vue'
import axios from 'axios';

// Axiosインターセプターを設定
axios.interceptors.request.use(
  config => {
    const jwt = localStorage.getItem('jwt');
    if (jwt) {
      config.headers.Authorization = `Bearer ${jwt}`;
    }
    return config;
  },
  error => {
    return Promise.reject(error);
  }
);

createApp(App).use(router).mount('#app')