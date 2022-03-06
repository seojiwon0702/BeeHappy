<template>
  <b-container class="bv-example-row">
    <b-row>
      <b-col><Search /></b-col>
    </b-row>
    <b-row>
      <b-col><Map /></b-col>
      <b-col><HouseList /></b-col>
    </b-row>
  </b-container>
</template>

<script>
  import Map from "@/components/house/Map.vue";
  import Search from "@/components/house/Search.vue";
  import HouseList from "@/components/house/HouseList.vue";
  //import HouseDetailModal from "@/components/house/HouseDetailModal.vue";

  export default {
    components: {
      Map,
      Search,
      HouseList,
    },
  };
</script>

<style>
  #map {
    height: 500px;
  }
</style>
