<template>
  <b-row class="mt-4 mb-4 text-center">
    <b-col class="sm-3">
      <b-form-select v-model="sidoCode" :options="sidos" @change="gugunList"></b-form-select>
    </b-col>
    <b-col class="sm-3">
      <b-form-select v-model="gugunCode" :options="guguns" @change="searchApt"></b-form-select>
    </b-col>
    <b-button variant="outline-warning" @click="$bvModal.show('advancedSearch')"
      >맞춤 설정</b-button
    >
    <b-modal id="advancedSearch" title="상세검색" hide-footer><AdvancedSearch /></b-modal>
  </b-row>
</template>

<script>
  import { mapState, mapActions, mapMutations } from "vuex";
  import AdvancedSearch from "@/components/house/AdvancedSearch.vue";

  export default {
    name: "HouseSearchBar",
    components: {
      AdvancedSearch,
    },
    data() {
      return {
        sidoCode: null,
        gugunCode: null,
      };
    },
    computed: {
      ...mapState("houseStore", ["sidos", "guguns"]),
    },
    created() {
      this.CLEAR_SIDO_LIST();
      this.getSido();
    },
    methods: {
      ...mapActions("houseStore", ["getSido", "getGugun", "getHouseList"]),
      ...mapMutations("houseStore", ["CLEAR_SIDO_LIST", "CLEAR_GUGUN_LIST", "CLEAR_HOUSE_LIST"]),
      gugunList() {
        this.CLEAR_GUGUN_LIST();
        this.gugunCode = null;
        if (this.sidoCode) this.getGugun(this.sidoCode);
      },
      searchApt() {
        this.CLEAR_HOUSE_LIST();
        if (this.gugunCode) this.getHouseList(this.gugunCode);
      },
    },
  };
</script>

<style></style>
