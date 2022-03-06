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
            <h3>부동산 뉴스</h3>
          </div>
        </b-alert>
      </b-col>
      <b-col />
    </b-row>
    <b-row class="mb-1"> </b-row>
    <b-row>
      <b-col>
        <!-- <b-table hover :items="news" :fields="fields"> </b-table> -->
        <b-container class="bv-example-row mt-5">
          <b-row v-for="(news_item, index) in news" :key="index" class="mb-3">
            <b-col sm="2"><b-img :src="news_item.image_url" /></b-col>
            <b-col>
              <b-row>{{ news_item.title }}</b-row>
              <b-row
                ><a :href="'https://land.naver.com/' + news_item.url" target="_blank"
                  >이동</a
                ></b-row
              >
            </b-col>
          </b-row>
        </b-container>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
  import axios from "axios";
  import cheerio from "cheerio";

  export default {
    data() {
      return {
        news: [],
        fields: [
          { key: "image_url", label: "이미지", tdClass: "tdClass" },
          { key: "title", label: "제목", tdClass: "tdSubject" },
        ],
      };
    },
    created() {
      this.getNews();
    },
    methods: {
      getNews() {
        return axios.get("https://land.naver.com/news/breaking.naver").then(({ data }) => {
          let $ = cheerio.load(data);

          let $list = $("ul.headline_list").children("li");
          $list.each((i, el) => {
            this.news.push({
              title: $(el).find("dl dt a").text(),
              url: $(el).find("dl dt a").attr("href"),
              image_url: $(el).find("dl dt.photo a img").attr("src"),
              image_alt: $(el).find("dl dt.photo a img").attr("alt"),
            });
          });
        });
      },
    },
  };
</script>

<style></style>
