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
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

header {
  background-color: #f8f8f8;
  padding: 20px;
  border-bottom: 1px solid #eee;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
  margin: 0 10px;
}

nav a.router-link-exact-active {
  color: #42b983;
}

main {
  padding: 20px;
}

footer {
  margin-top: 40px;
  padding: 20px;
  background-color: #f8f8f8;
  border-top: 1px solid #eee;
  color: #777;
}
</style>