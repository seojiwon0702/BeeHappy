<template>
  <b-container class="bv-example-row mt-3">
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button variant="outline-warning" @click="listArticle">목록</b-button>
      </b-col>
      <b-col class="text-right">
        <b-button
          variant="outline-info"
          size="sm"
          @click="moveModifyArticle"
          class="mr-2"
          v-if="userInfo != null && userInfo.userid == qna.writer"
          >글수정</b-button
        >
        <b-button
          variant="outline-danger"
          size="sm"
          @click="removeArticle"
          v-if="userInfo != null && userInfo.userid == qna.writer"
          >글삭제</b-button
        >
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col>
        <b-card
          :header-html="`<h3>${qna.no}.
          ${qna.subject} </h3><div><h6>${qna.writer}</div><div>${qna.regtime}</h6></div>`"
          class="mb-2"
          border-variant="dark"
          no-body
        >
          <b-card-body class="text-left">
            <div v-html="message"></div>
          </b-card-body>
        </b-card>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
  // import moment from "moment";
  import { mapState, mapActions } from "vuex";

  const qnaStore = "qnaStore";

  export default {
    computed: {
      ...mapState(qnaStore, ["qna"]),
      ...mapState("userStore", ["userInfo"]),
      message() {
        if (this.qna.content) return this.qna.content.split("\n").join("<br>");
        return "";
      },
    },
    methods: {
      ...mapActions(qnaStore, ["deleteQnA"]),
      listArticle() {
        this.$router.push({ name: "QnAList" });
      },
      moveModifyArticle() {
        this.$router.replace({
          name: "QnAModify",
          params: { articleno: this.qna.no },
        });
      },
      removeArticle() {
        if (confirm("삭제하시겠습니까?")) {
          this.deleteQnA(this.qna.no);
          this.listArticle();
        }
      },
    },
  };
</script>

<style></style>
