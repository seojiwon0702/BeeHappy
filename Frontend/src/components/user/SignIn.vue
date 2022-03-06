<template>
  <b-container class="bv-example-row mt-5">
    <b-row>
      <b-col />
      <b-col>
        <b-alert variant="warning" align="center" show>
          <div>
            <img
              src="@/assets/logo.png"
              class="d-inline-block align-middle"
              width="45px"
              alt="Bee"
            />
            <h3>Bee Happy</h3>
          </div>
        </b-alert>
      </b-col>
      <b-col />
    </b-row>
    <b-row class="mt-5">
      <b-col />
      <b-col cols="5">
        <b-form class="text-left">
          <b-alert show variant="danger" v-if="isLoginError"
            >아이디 또는 비밀번호를 확인하세요.</b-alert
          >
          <b-form-group>
            <b-form-input
              id="userid"
              v-model="user.userid"
              required
              placeholder="Bee Happy ID"
              @keyup.enter="confirm"
              size="lg"
            ></b-form-input>
          </b-form-group>
          <b-form-group>
            <b-form-input
              type="password"
              id="userpwd"
              v-model="user.userpwd"
              required
              placeholder="비밀번호"
              @keyup.enter="confirm"
              size="lg"
            ></b-form-input>
          </b-form-group>
          <b-button
            block
            type="button"
            size="lg"
            variant="outline-warning"
            class="m-1"
            @click="confirm"
            >로그인</b-button
          >
        </b-form>
      </b-col>
      <b-col />
    </b-row>
  </b-container>
</template>

<script>
  import { mapState, mapActions, mapMutations } from "vuex";

  const userStore = "userStore";

  export default {
    data() {
      return {
        user: {
          userid: null,
          userpwd: null,
        },
      };
    },
    created() {
      this.SET_IS_LOGIN_ERROR(false);
    },
    computed: {
      ...mapState(userStore, ["isLogin", "isLoginError"]),
    },
    methods: {
      ...mapActions(userStore, ["userConfirm", "getUserInfo"]),
      ...mapMutations(userStore, ["SET_IS_LOGIN_ERROR"]),
      async confirm() {
        await this.userConfirm(this.user);
        let token = sessionStorage.getItem("access-token");
        if (this.isLogin) {
          await this.getUserInfo(token);
          this.$router.push({ name: "Home" });
        }
      },
    },
  };
</script>

<style></style>
