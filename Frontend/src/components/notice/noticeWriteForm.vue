<template>
  <b-row class="mb-1">
    <b-col style="text-align: right">
      <b-form @submit="onSubmit">
        <b-form-group id="writer-group" label-for="writer">
          <b-form-input
            id="writer"
            :disabled="true"
            v-model="article.writer"
            type="text"
            required
          ></b-form-input>
        </b-form-group>

        <b-form-group id="subject-group" label-for="subject">
          <b-form-input
            id="subject"
            v-model="article.subject"
            type="text"
            required
            placeholder="제목"
          ></b-form-input>
        </b-form-group>

        <b-form-group id="content-group" label-for="content">
          <b-form-textarea
            id="content"
            v-model="article.content"
            placeholder="내용"
            rows="10"
            max-rows="15"
          ></b-form-textarea>
        </b-form-group>

        <b-button
          type="submit"
          variant="outline-warning"
          class="m-1"
          v-if="this.type === 'register'"
          >글작성</b-button
        >
        <b-button type="submit" variant="outline-warning" class="m-1" v-else>글수정</b-button>
      </b-form>
    </b-col>
  </b-row>
</template>

<script>
  import { mapState, mapActions } from "vuex";

  const noticeStore = "noticeStore";

  export default {
    props: {
      type: { type: String },
    },
    data() {
      return {
        article: {
          writer: "",
          subject: "",
          content: "",
        },
      };
    },
    computed: {
      ...mapState(noticeStore, ["notice"]),
      ...mapState("userStore", ["userInfo"]),
    },
    created() {
      if (this.type === "modify") {
        this.article = this.notice;
      } else {
        this.article.writer = this.userInfo.userid;
      }
    },
    methods: {
      ...mapActions(noticeStore, ["modifyNotice", "addNotice"]),
      onSubmit(event) {
        event.preventDefault();

        let err = true;
        let msg = "";
        !this.article.writer &&
          ((msg = "작성자 입력해주세요"), (err = false), this.$refs.writer.focus());
        err &&
          !this.article.subject &&
          ((msg = "제목 입력해주세요"), (err = false), this.$refs.subject.focus());
        err &&
          !this.article.content &&
          ((msg = "내용 입력해주세요"), (err = false), this.$refs.content.focus());

        if (!err) alert(msg);
        else this.type === "register" ? this.registArticle() : this.updateArticle();
      },
      registArticle() {
        this.addNotice(this.article);
        this.moveList();
      },
      updateArticle() {
        this.modifyNotice(this.article);
        this.moveList();
      },
      moveList() {
        this.$router.push({ name: "NoticeList" });
      },
    },
  };
</script>

<style></style>
