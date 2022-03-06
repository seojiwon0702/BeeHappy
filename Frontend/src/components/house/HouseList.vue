<template>
  <b-container v-if="houses && houses.length != 0" class="bv-example-row">
    <house-list-row v-for="(house, index) in houses" :key="index" :house="house" />
    <b-modal id="houseDetail" title="상세보기" hide-footer><HouseDetailModal /> </b-modal>
  </b-container>
  <b-container v-else class="bv-example-row">
    <b-row>
      <b-col><b-alert variant="warning" show>주택 목록이 없습니다.</b-alert></b-col>
    </b-row>
  </b-container>
</template>

<script>
  import HouseListRow from "@/components/house/HouseListRow.vue";
  import HouseDetailModal from "@/components/house/HouseDetailModal.vue";
  import { mapState, mapMutations } from "vuex";

  const houseStore = "houseStore";

  export default {
    name: "HouseList",
    components: {
      HouseListRow,
      HouseDetailModal,
    },
    data() {
      return {
        localHouseList: [],
      };
    },
    computed: {
      ...mapState(houseStore, ["houses", "house"]),
    },
    methods: {
      ...mapMutations(houseStore, ["CLEAR_HOUSE_LIST"]),
    },
    created() {
      this.CLEAR_HOUSE_LIST();
    },
  };
</script>
