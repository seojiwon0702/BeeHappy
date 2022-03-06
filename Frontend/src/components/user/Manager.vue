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
            <h3>회원관리</h3>
          </div>
        </b-alert>
      </b-col>
      <b-col />
    </b-row>
    <b-row class="mb-1"> </b-row>
    <b-row>
      <b-col>
        <b-table hover :items="userList" :fields="fields" @row-clicked="authorization"> </b-table>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
  import { mapState, mapActions } from "vuex";

  const userStore = "userStore";
  export default {
    data() {
      return {
        fields: [
          { key: "userid", label: "아이디", tdClass: "tdClass" },
          { key: "username", label: "이름", tdClass: "tdClass" },
          { key: "auth", label: "권한", tdClass: "tdClass" },
        ],
      };
    },
    computed: {
      ...mapState(userStore, ["userList"]),
    },
    methods: {
      ...mapActions(userStore, ["getUserList", "updateUser"]),
      authorization(user) {
        if (user.auth == 1) {
          if (confirm(`${user.username} 님은 이미 권한이 부여되어 있습니다.`)) {
            if (confirm(`${user.username} 님의 권한을 해제하시겠습니까?`)) {
              user.auth = false;
              this.updateUser(user);
            }
          }
        } else {
          if (confirm(`${user.username}에게 권한을 부여하시겠습니까?`)) {
            user.auth = true;
            this.updateUser(user);
          }
        }
      },
    },
    created() {
      this.getUserList();
    },
  };
</script>

<style></style>
