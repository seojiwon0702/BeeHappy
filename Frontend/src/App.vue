<template>
  <div id="app">
    <Header />
    <router-view />
  </div>
</template>

<script>
  import Header from "@/components/common/Header.vue";
  export default {
    components: {
      Header,
    },
  };
</script>

<style>
  a:hover {
    text-decoration: none;
    font-weight: bold;
  }
  a.router-link-exact-active {
    color: #f8f8f8;
    font-weight: bold;
    display: inline-block;
    background: linear-gradient(180deg, rgba(255, 255, 255, 0) 90%, rgb(253, 253, 253) 10%);
  }
</style>
