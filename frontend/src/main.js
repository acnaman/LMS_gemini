import '@digital-go-jp/design-tokens/dist/tokens.css'; // Corrected path
import 'bootstrap/dist/css/bootstrap.min.css';

import { createApp } from 'vue'
import { createRouter, createWebHistory } from 'vue-router'
import App from './App.vue'

// Placeholder components for routing
import UserManagement from './views/UserManagementView.vue'
import CourseManagement from './views/CourseManagementView.vue'
import EnrollmentForm from './views/EnrollmentView.vue'
import LearningHistory from './views/LearningHistoryView.vue'

const routes = [
  { path: '/admin/users', component: UserManagement },
  { path: '/admin/courses', component: CourseManagement },
  { path: '/user/enrollments', component: EnrollmentForm },
  { path: '/user/history', component: LearningHistory },
  { path: '/', redirect: '/admin/users' } // Default route
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

createApp(App).use(router).mount('#app')