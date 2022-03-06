import jwt_decode from "jwt-decode";
import {
  login,
  findById,
  signUp,
  countId,
  updateUser,
  deleteUser,
  getUserList,
} from "@/api/user.js";

const userStore = {
  namespaced: true,
  state: {
    isLogin: false,
    isLoginError: false,
    isUsingId: false,
    userInfo: null,
    userList: [],
  },
  getters: {
    checkUserInfo: function (state) {
      return state.userInfo;
    },
  },
  mutations: {
    SET_IS_LOGIN: (state, isLogin) => {
      state.isLogin = isLogin;
    },
    SET_IS_LOGIN_ERROR: (state, isLoginError) => {
      state.isLoginError = isLoginError;
    },
    SET_USER_INFO: (state, userInfo) => {
      state.isLogin = true;
      state.userInfo = userInfo;
    },
    SET_ISUSING_ID: (state, isUsingID) => {
      state.isUsingId = isUsingID;
    },
    SET_USER_LIST: (state, userList) => {
      state.userList = userList;
    },
    CLEAR_USER_LIST: (state) => {
      state.userList = [];
    },
  },
  actions: {
    async userConfirm({ commit }, user) {
      await login(
        user,
        (response) => {
          if (response.data.message === "success") {
            let token = response.data["access-token"];
            commit("SET_IS_LOGIN", true);
            commit("SET_IS_LOGIN_ERROR", false);
            sessionStorage.setItem("access-token", token);
          } else {
            commit("SET_IS_LOGIN", false);
            commit("SET_IS_LOGIN_ERROR", true);
          }
        },
        () => {}
      );
    },
    getUserInfo({ commit }, token) {
      let decode_token = jwt_decode(token);
      findById(
        decode_token.userid,
        (response) => {
          if (response.data.message === "success") {
            commit("SET_USER_INFO", response.data.userInfo);
          } else {
            console.log("유저 정보 없음!!");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
    idCheck({ commit }, userinfo) {
      countId(
        userinfo.userid,
        (response) => {
          if (response.data == "success") {
            commit("SET_ISUSING_ID", false);
          } else {
            commit("SET_ISUSING_ID", true);
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },

    signUp({ commit }, userinfo) {
      signUp(userinfo);
      commit("SET_ISUSING_ID", false);
    },

    updateUser: async (context, user) => {
      await updateUser(user);
    },

    withdraw: async (context, userid) => {
      await deleteUser(userid);
    },

    getUserList: async ({ commit }) => {
      getUserList(
        ({ data }) => {
          commit("CLEAR_USER_LIST");
          commit("SET_USER_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};

export default userStore;
