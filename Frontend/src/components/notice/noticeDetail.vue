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
          v-if="userInfo != null && userInfo.userid == notice.writer"
          >글수정</b-button
        >
        <b-button
          variant="outline-danger"
          size="sm"
          @click="removeArticle"
          v-if="userInfo != null && userInfo.userid == notice.writer"
          >글삭제</b-button
        >
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col>
        <b-card
          :header-html="`<h3>${notice.no}.
          ${notice.subject} </h3><div><h6>${notice.writer}</div><div>${notice.regtime}</h6></div>`"
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
  import { mapState, mapActions } from "vuex";

  const noticeStore = "noticeStore";

  export default {
    computed: {
      ...mapState(noticeStore, ["notice"]),
      ...mapState("userStore", ["userInfo"]),
      message() {
        if (this.notice.content) return this.notice.content.split("\n").join("<br>");
        return "";
      },
    },
    methods: {
      ...mapActions(noticeStore, ["deleteNotice"]),
      listArticle() {
        this.$router.push({ name: "NoticeList" });
      },
      moveModifyArticle() {
        this.$router.replace({
          name: "NoticeModify",
          params: { articleno: this.notice.no },
        });
      },
      removeArticle() {
        if (confirm("삭제하시겠습니까?")) {
          this.deleteNotice(this.notice.no);
          this.listArticle();
        }
      },
    },
  };
</script>

<style></style>
