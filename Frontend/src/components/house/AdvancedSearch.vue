<template>
  <div>
    <b-form-group label="최대 가격">
      <b-form-input v-model="maxprice" type="number" />
    </b-form-group>
    <b-form-group label="최소 면적">
      <b-form-input v-model="minarea" type="number" />
    </b-form-group>
    <b-button class="mt-3" block variant="outline-warning" @click="clear">초기화</b-button>
    <b-button class="mt-3" block variant="warning" @click="[set(), $bvModal.hide('advancedSearch')]"
      >저장</b-button
    >
  </div>
</template>

<script>
  import { mapState, mapMutations } from "vuex";

  const houseStore = "houseStore";

  export default {
    data() {
      return {
        minarea: null,
        maxprice: null,
        localHouseList: [],
      };
    },
    created() {
      this.minarea = this.minArea;
      this.maxprice = this.maxPrice;
    },
    computed: {
      ...mapState(houseStore, ["maxPrice", "minArea", "houses"]),
    },
    methods: {
      ...mapMutations(houseStore, [
        "CLEAR_MAX_PRICE",
        "CLEAR_MIN_AREA",
        "SET_MAX_PRICE",
        "SET_MIN_AREA",
        "SET_HOUSE_LIST",
      ]),
      clear() {
        this.CLEAR_MAX_PRICE();
        this.CLEAR_MIN_AREA();
        this.minarea = this.minArea;
        this.maxprice = this.maxPrice;
      },
      set() {
        this.SET_MAX_PRICE(this.maxprice);
        this.SET_MIN_AREA(this.minarea);
        console.log(this.houses.length);
        for (let i = 0; i < this.houses.length; i++) {
          // console.log(this.houses[i].거래금액 + " " + this.houses[i].전용면적);
          let price = parseInt(this.houses[i].거래금액.replace(",", "")) * 10000;
          console.log("거래금액: " + price + " " + "전용면적: " + this.houses[i].전용면적);
          if (price <= this.maxPrice && this.houses[i].전용면적 >= this.minArea) {
            this.localHouseList.push(this.houses[i]);
          }
        }
        this.SET_HOUSE_LIST(this.localHouseList);
      },
    },
  };
</script>

<style></style>
