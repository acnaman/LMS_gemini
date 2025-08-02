import { createRouter, createWebHistory } from 'vue-router';
import LoginView from '../views/LoginView.vue';
import UserManagement from '../views/UserManagementView.vue'
import CourseManagement from '../views/CourseManagementView.vue'
import EnrollmentForm from '../views/EnrollmentView.vue'
import LearningHistory from '../views/LearningHistoryView.vue'

const routes = [
  {
    path: '/login',
    name: 'Login',
    component: LoginView,
  },
  {
    path: '/admin/users',
    name: 'UserManagement',
    component: UserManagement,
    meta: { requiresAuth: true }
  },
  {
    path: '/admin/courses',
    name: 'CourseManagement',
    component: CourseManagement,
    meta: { requiresAuth: true }
  },
  {
    path: '/user/enrollments',
    name: 'EnrollmentForm',
    component: EnrollmentForm,
    meta: { requiresAuth: true }
  },
  {
    path: '/user/history',
    name: 'LearningHistory',
    component: LearningHistory,
    meta: { requiresAuth: true }
  },
  {
    path: '/',
    redirect: '/admin/users'
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

router.beforeEach((to, from, next) => {
  const loggedIn = localStorage.getItem('jwt');
  if (to.matched.some(record => record.meta.requiresAuth) && !loggedIn) {
    next('/login');
  } else {
    next();
  }
});

export default router;
