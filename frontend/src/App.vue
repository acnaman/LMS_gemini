<template>
  <div class="d-flex">
    <div class="sidebar d-flex flex-column flex-shrink-0 p-3">
      <a href="/" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-white text-decoration-none">
        <i class="bi bi-box-seam-fill me-2"></i>
        <span class="fs-4">LMS</span>
      </a>
      <hr>
      <ul class="nav nav-pills flex-column mb-auto">
        <li class="nav-item">
          <router-link to="/admin/users" class="nav-link" active-class="active">
            <i class="bi bi-people-fill me-2"></i>
            ユーザー管理
          </router-link>
        </li>
        <li>
          <router-link to="/admin/courses" class="nav-link" active-class="active">
            <i class="bi bi-journal-bookmark-fill me-2"></i>
            コース管理
          </router-link>
        </li>
        <li>
          <router-link to="/user/enrollments" class="nav-link" active-class="active">
            <i class="bi bi-card-checklist me-2"></i>
            受講登録
          </router-link>
        </li>
        <li>
          <router-link to="/user/history" class="nav-link" active-class="active">
            <i class="bi bi-clock-history me-2"></i>
            学習履歴
          </router-link>
        </li>
      </ul>
      <hr>
      <div class="dropdown">
        <a href="#" class="d-flex align-items-center text-white text-decoration-none dropdown-toggle" id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">
          <img src="https://github.com/mdo.png" alt="" width="32" height="32" class="rounded-circle me-2">
          <strong>山田 太郎</strong>
        </a>
        <ul class="dropdown-menu dropdown-menu-dark text-small shadow" aria-labelledby="dropdownUser1">
          <li><a class="dropdown-item" href="#">設定</a></li>
          <li><a class="dropdown-item" href="#">プロフィール</a></li>
          <li><hr class="dropdown-divider"></li>
          <li><a class="dropdown-item" href="#">サインアウト</a></li>
        </ul>
      </div>
    </div>

    <div class="content">
      <router-view></router-view>
    </div>
  </div>
</template>

<script setup>
</script>

<style>
@import "https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css";
@import "https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css";

body {
  min-height: 100vh;
  display: flex;
}
#app {
  display: flex;
  width: 100%;
}
.sidebar {
  width: 280px;
  background: #343a40;
  color: white;
}
.content {
  flex-grow: 1;
  padding: 2rem;
}
.nav-link {
  color: #adb5bd;
}
.nav-link.active {
  color: white;
  background-color: #495057;
}
.nav-link:hover {
  color: white;
  background-color: #495057;
}
</style>
