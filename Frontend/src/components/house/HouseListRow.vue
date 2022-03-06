<template>
  <b-row @click="[selectHouse(), $bvModal.show('houseDetail')]">
    <b-col cols="10" class="align-self-center item">
      <b-alert show variant="warning"> [{{ house.일련번호 }}] {{ house.아파트 }} </b-alert>
    </b-col>
  </b-row>
</template>

<script>
  import { mapActions } from "vuex";

  const houseStore = "houseStore";

  export default {
    name: "HouseListRow",
    data() {
      return {
        isColor: false,
      };
    },
    props: {
      house: Object,
    },
    methods: {
      ...mapActions(houseStore, ["detailHouse"]),
      selectHouse() {
        this.detailHouse(this.house);
      },
    },
  };
</script>

<style scoped>
  .item {
    font-size: 2ch;
    font-weight: bold;
  }
</style>
