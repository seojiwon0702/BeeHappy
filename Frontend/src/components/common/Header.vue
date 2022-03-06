<template>
  <div>
    <b-navbar toggleable="lg" type="dark" variant="warning">
      <b-navbar-brand tag="h1" class="mb-0">
        <router-link :to="{ name: 'Home' }" class="link"
          ><img
            src="@/assets/logo.png"
            class="d-inline-block align-middle"
            width="45px"
            alt="Bee"
          />BeeHappy</router-link
        >
      </b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item
            ><router-link :to="{ name: 'Notice' }" class="link">공지사항</router-link></b-nav-item
          >
          <b-nav-item
            ><router-link :to="{ name: 'House' }" class="link">내 집 마련</router-link></b-nav-item
          >
          <b-nav-item><router-link :to="{ name: 'QnA' }" class="link">QnA</router-link></b-nav-item>
          <b-nav-item
            ><router-link :to="{ name: 'News' }" class="link">News</router-link></b-nav-item
          >
        </b-navbar-nav>

        <!-- 로그인 후 -->
        <b-navbar-nav class="ml-auto" v-if="userInfo">
          <b-nav-form>
            <b-nav-item
              ><router-link :to="{ name: 'UserInfo' }" class="link"
                >{{ userInfo.username }} 님 반갑습니다.</router-link
              ></b-nav-item
            ><b-nav-item v-if="userInfo.auth == 1"
              ><router-link :to="{ name: 'Manager' }" class="link"
                >회원 관리</router-link
              ></b-nav-item
            >
            <b-nav-item class="link" @click.prevent="onClickLogout">로그아웃</b-nav-item>
          </b-nav-form>
        </b-navbar-nav>

        <!-- 로그인 전 -->
        <b-navbar-nav class="ml-auto" v-else>
          <b-nav-form>
            <b-nav-item
              ><router-link :to="{ name: 'SignIn' }" class="link">로그인</router-link></b-nav-item
            >
            <b-nav-item
              ><router-link :to="{ name: 'SignUp' }" class="link">회원가입</router-link></b-nav-item
            >
          </b-nav-form>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
  import { mapState, mapMutations } from "vuex";

  const userStore = "userStore";

  export default {
    computed: {
      ...mapState(userStore, ["isLogin", "userInfo"]),
    },
    methods: {
      ...mapMutations(userStore, ["SET_IS_LOGIN", "SET_USER_INFO", "CLEAR_USER_LIST"]),
      onClickLogout() {
        this.SET_IS_LOGIN(false);
        this.SET_USER_INFO(null);
        this.CLEAR_USER_LIST();
        sessionStorage.removeItem("access-token");
        if (this.$route.path != "/") this.$router.push({ name: "Home" });
      },
    },
  };
</script>

<style scoped>
  .link {
    color: #f8f8f8;
    font-size: 20px;
  }
</style>
