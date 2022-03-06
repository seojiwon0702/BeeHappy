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
            <h3>회원 정보</h3>
          </div>
        </b-alert>
      </b-col>
      <b-col />
    </b-row>
    <b-row>
      <b-col />
      <b-col cols="5">
        <b-form class="text-left">
          <b-form-group>
            <b-form-input
              v-model="userInfo.userid"
              :disabled="true"
              required
              placeholder="Bee Happy ID"
              size="lg"
            ></b-form-input>
          </b-form-group>
          <b-form-group>
            <b-form-input
              v-model="userInfo.username"
              placeholder="사용자 이름"
              required
              size="lg"
            ></b-form-input>
          </b-form-group>
          <b-form-group>
            <b-form-input
              type="password"
              v-model="userInfo.userpwd"
              required
              placeholder="비밀번호"
              size="lg"
            ></b-form-input>
          </b-form-group>
          <b-form-group>
            <b-form-input
              type="text"
              v-model="userInfo.address"
              placeholder="주소 (예. 서울특별시 강남구 테헤란로 212)"
              required
            ></b-form-input>
          </b-form-group>
          <b-form-group>
            <b-form-input
              type="email"
              v-model="userInfo.email"
              placeholder="이메일(예. ssafy@ssafy.com)"
              required
              size="lg"
            ></b-form-input>
          </b-form-group>
          <b-button
            block
            type="button"
            size="lg"
            variant="outline-warning"
            class="m-1"
            @click="submit"
            >정보수정</b-button
          >
          <b-button
            block
            type="button"
            size="lg"
            variant="outline-danger"
            class="m-1"
            @click="withDraw"
            >회원탈퇴</b-button
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
    computed: {
      ...mapState(userStore, ["userInfo"]),
    },
    methods: {
      ...mapActions(userStore, ["updateUser", "withdraw", "getUserInfo"]),
      ...mapMutations(userStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
      async submit() {
        await this.updateUser(this.userInfo);
        let token = sessionStorage.getItem("access-token");
        sessionStorage.removeItem("access-token");
        await this.getUserInfo(token);
        this.$router.push({ name: "Home" });
      },
      async withDraw() {
        if (confirm("정말 탈퇴하시겠습니까?")) {
          await this.withdraw(this.userInfo.userid);
          this.SET_IS_LOGIN(false);
          this.SET_USER_INFO(null);
          sessionStorage.removeItem("access-token");
          if (this.$route.path != "/") this.$router.push({ name: "Home" });
        }
      },
    },
  };
</script>

<style></style>
