<template>
  <div>
    <b-row class="mb-1">
      <b-col class="text-right">
        <b-button variant="outline-warning" @click="moveWrite()" v-if="userInfo != null"
          >글쓰기</b-button
        >
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-table hover :items="qnaList" :fields="fields" @row-clicked="detailArticle"> </b-table>
      </b-col>
    </b-row>
  </div>
</template>

<script>
  import { mapState, mapActions } from "vuex";

  const qnaStore = "qnaStore";

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
      ...mapState(qnaStore, ["qnaList"]),
      ...mapState("userStore", ["userInfo"]),
    },
    created() {
      if (this.qnaList.length == 0) this.getQnaList();
    },
    methods: {
      ...mapActions(qnaStore, ["getQnaList", "getQnA"]),
      moveWrite() {
        this.$router.push({ name: "QnAWrite" });
      },
      detailArticle(article) {
        this.getQnA(article.no);
        this.$router.push({
          name: "QnADetail",
          params: { articleno: article.no },
        });
      },
    },
  };
</script>
