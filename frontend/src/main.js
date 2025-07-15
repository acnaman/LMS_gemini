import './assets/main.css'

import { createApp } from 'vue'
import { createRouter, createWebHistory } from 'vue-router'
import App from './App.vue'

// Placeholder components for routing
import UserManagement from './views/UserManagementView.vue'
const CourseManagement = { template: '<div>講座管理画面</div>' }
const EnrollmentForm = { template: '<div>受講申込画面</div>' }
const LearningHistory = { template: '<div>受講履歴画面</div>' }

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