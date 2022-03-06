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
    <b-row>
      <b-col />
      <b-col cols="5">
        <b-form class="text-left">
          <b-alert show variant="danger" v-if="isUsingId">사용중인 아이디 입니다.</b-alert>
          <b-alert show variant="danger" v-if="isCollect">비밀번호가 일치하지 않습니다.</b-alert>
          <b-form-group>
            <b-form-input
              v-model="user.userid"
              required
              placeholder="Bee Happy ID"
              @keyup="idChecking"
              size="lg"
            ></b-form-input>
          </b-form-group>
          <b-form-group>
            <b-form-input
              v-model="user.username"
              required
              placeholder="사용자 이름"
              size="lg"
            ></b-form-input>
          </b-form-group>
          <b-form-group>
            <b-form-input
              type="password"
              v-model="user.userpwd"
              required
              placeholder="비밀번호"
              size="lg"
            ></b-form-input>
          </b-form-group>
          <b-form-group>
            <b-form-input
              type="password"
              v-model="checkpwd"
              required
              placeholder="비밀번호 재입력"
              @keyup="pwdChecking"
              size="lg"
            ></b-form-input>
          </b-form-group>
          <b-form-group>
            <b-form-input
              type="text"
              v-model="user.useraddress"
              required
              placeholder="주소 (예. 서울특별시 강남구 테헤란로 212)"
            ></b-form-input>
          </b-form-group>
          <b-form-group>
            <b-form-input
              type="email"
              v-model="user.useremail"
              required
              placeholder="이메일(예. ssafy@ssafy.com)"
              size="lg"
            ></b-form-input>
          </b-form-group>
          <b-button
            block
            type="button"
            size="lg"
            variant="outline-warning"
            class="m-1"
            @click="join"
            >회원가입</b-button
          >
        </b-form>
      </b-col>
      <b-col />
    </b-row>
  </b-container>
</template>

<script>
  import { mapActions, mapState } from "vuex";

  const userStore = "userStore";

  export default {
    data() {
      return {
        user: {
          userid: null,
          userpwd: null,
          username: null,
          useremail: null,
          useraddress: null,
        },
        checkpwd: null,
        isCollect: false,
      };
    },
    computed: {
      ...mapState(userStore, ["isUsingId"]),
    },
    methods: {
      ...mapActions(userStore, ["idCheck", "signUp"]),
      idChecking() {
        this.idCheck(this.user);
      },
      pwdChecking() {
        if (this.user.userpwd == this.checkpwd) {
          this.isCollect = false;
        } else {
          this.isCollect = true;
        }
      },
      async join() {
        if (!this.isCollect && !this.isUsingId) {
          await this.signUp(this.user);
          this.$router.push({ name: "SignIn" });
        }
      },
    },
  };
</script>

<style></style>
