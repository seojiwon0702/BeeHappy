import { listQnA, writeQnA, getQnA, modifyQnA, deleteQnA } from "@/api/qna.js";

const qnaStore = {
  namespaced: true,
  state: {
    qnaList: [],
    qna: null,
  },
  getters: {},
  mutations: {
    SET_QNA_LIST: (state, qnaList) => {
      qnaList.forEach((qna) => {
        state.qnaList.push(qna);
      });
    },
    SET_QNA: (state, qna) => {
      state.qna = qna;
    },
    CLEAR_QNA_LIST: (state) => {
      state.qnaList = [];
    },
  },
  actions: {
    getQnaList: ({ commit }) => {
      listQnA(
        ({ data }) => {
          commit("CLEAR_QNA_LIST");
          commit("SET_QNA_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getQnA: ({ commit }, articleNo) => {
      getQnA(
        articleNo,
        ({ data }) => {
          commit("SET_QNA", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    addQnA: async (context, article) => {
      await writeQnA(article);
      context.dispatch("getQnaList");
    },
    deleteQnA: async (context, articleNo) => {
      await deleteQnA(articleNo);
      context.dispatch("getQnaList");
    },
    modifyQnA: async (context, article) => {
      await modifyQnA(article);
      context.dispatch("getQnaList");
    },
  },
};

export default qnaStore;
