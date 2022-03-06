import { listNotice, writeNotice, getNotice, modifyNotice, deleteNotice } from "@/api/notice.js";

const noticeStore = {
  namespaced: true,
  state: {
    noticeList: [],
    notice: null,
  },
  getters: {},
  mutations: {
    SET_NOTICE_LIST: (state, noticeList) => {
      noticeList.forEach((notice) => {
        state.noticeList.push(notice);
      });
    },
    SET_NOTICE: (state, notice) => {
      state.notice = notice;
    },
    CLEAR_NOTICE_LIST: (state) => {
      state.noticeList = [];
    },
  },
  actions: {
    getNoticeList: ({ commit }) => {
      listNotice(
        ({ data }) => {
          commit("CLEAR_NOTICE_LIST");
          commit("SET_NOTICE_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getNotice: ({ commit }, articleNo) => {
      getNotice(
        articleNo,
        ({ data }) => {
          commit("SET_NOTICE", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    addNotice: async (context, article) => {
      await writeNotice(article);
      context.dispatch("getNoticeList");
    },
    deleteNotice: async (context, articleNo) => {
      await deleteNotice(articleNo);
      context.dispatch("getNoticeList");
    },
    modifyNotice: async (context, article) => {
      await modifyNotice(article);
      context.dispatch("getNoticeList");
    },
  },
};

export default noticeStore;
