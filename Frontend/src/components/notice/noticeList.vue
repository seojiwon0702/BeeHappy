<template>
  <div>
    <b-row class="mb-1">
      <b-col class="text-right">
        <b-button
          variant="outline-warning"
          @click="moveWrite()"
          v-if="userInfo != null && userInfo.auth == 1"
          >글쓰기</b-button
        >
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-table hover :items="noticeList" :fields="fields" @row-clicked="detailArticle"> </b-table>
      </b-col>
    </b-row>
  </div>
</template>

<script>
  import { mapState, mapActions } from "vuex";

  const noticeStore = "noticeStore";

  export default {
    data() {
      return {
        fields: [
          { key: "no", label: "글번호", tdClass: "tdClass" },
          { key: "subject", label: "제목", tdClass: "tdSubject" },
          { key: "writer", label: "작성자", tdClass: "tdClass" },
          { key: "regtime", label: "작성일", tdClass: "tdClass" },
        ],
      };
    },
    computed: {
      ...mapState(noticeStore, ["noticeList"]),
      ...mapState("userStore", ["userInfo"]),
    },
    created() {
      if (this.noticeList.length == 0) this.getNoticeList();
    },
    methods: {
      ...mapActions(noticeStore, ["getNoticeList", "getNotice"]),
      moveWrite() {
        this.$router.push({ name: "NoticeWrite" });
      },
      detailArticle(article) {
        this.getNotice(article.no);
        this.$router.push({
          name: "NoticeDetail",
          params: { articleno: article.no },
        });
      },
    },
  };
</script>
