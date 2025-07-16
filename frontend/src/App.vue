<template>
  <div id="app">
    <header>
      <h1>Learn Forest</h1>
      <nav>
        <router-link to="/admin/users">ユーザー管理</router-link> |
        <router-link to="/admin/courses">講座管理</router-link> |
        <router-link to="/user/enrollments">受講申込</router-link> |
        <router-link to="/user/history">受講履歴</router-link>
      </nav>
    </header>
    <main>
      <router-view></router-view>
    </main>
    <footer>
      <p>&copy; 2024 Learn Forest</p>
    </footer>
  </div>
</template>

<script setup>
// No specific script logic needed for basic layout
</script>

<style>
#app {
  font-family: var(--jps-font-family-base);
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: var(--jps-color-text-primary);
}

header {
  background-color: var(--jps-color-background-primary);
  padding: var(--jps-spacing-lg);
  border-bottom: 1px solid var(--jps-color-border-divider);
}

nav a {
  font-weight: var(--jps-font-weight-bold);
  color: var(--jps-color-text-primary);
  margin: 0 var(--jps-spacing-md);
}

nav a.router-link-exact-active {
  color: var(--jps-color-text-link-active);
}

main {
  padding: var(--jps-spacing-lg);
}

footer {
  margin-top: var(--jps-spacing-xl);
  padding: var(--jps-spacing-lg);
  background-color: var(--jps-color-background-primary);
  border-top: 1px solid var(--jps-color-border-divider);
  color: var(--jps-color-text-secondary);
}
</style>
