<template>
  <div id="map" />
</template>

<script>
  import { mapState } from "vuex";

  const houseStore = "houseStore";

  export default {
    data() {
      return {
        map: null,
        geocoder: null,
        markers: [],
      };
    },
    mounted() {
      if (window.kakao && window.kakao.maps) {
        this.initMap();
      } else {
        const script = document.createElement("script");
        script.onload = () => kakao.maps.load(this.initMap);
        script.src =
          "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=ca7b349fbb0baaa0dc8a8c3c37d870d2&libraries=services";
        document.head.appendChild(script);
      }
    },
    computed: {
      ...mapState(houseStore, ["houses"]),
    },
    watch: {
      houses() {
        this.addMarkerOnMap();
      },
    },
    methods: {
      initMap() {
        const container = document.getElementById("map");
        const options = {
          center: new kakao.maps.LatLng(37.50136313997764, 127.03967476819672),
          level: 5,
        };
        this.map = new kakao.maps.Map(container, options);
      },

      addMarker(position) {
        var marker = new kakao.maps.Marker({
          position: position,
        });
        marker.setMap(this.map);
        this.markers.push(marker);
        this.map.setCenter(position);
      },

      addMarkerOnMap() {
        this.clearMarkers();
        this.geocoder = new kakao.maps.services.Geocoder();
        for (let i = 0; i < this.houses.length; i++) {
          this.geocoder.addressSearch(
            `${this.houses[i].법정동} ${this.houses[i].지번}번지`,
            (result, status) => {
              // 정상적으로 검색이 완료됐으면
              if (status === kakao.maps.services.Status.OK) {
                var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
                // 결과값으로 받은 위치를 마커로 표시합니다
                this.addMarker(coords);
              }
            }
          );
        }
      },

      clearMarkers() {
        for (let i = 0; i < this.markers.length; i++) {
          this.markers[i].setMap(null);
        }
        this.markers = [];
      },
    },
  };
</script>

<style>
  #map {
    width: 100%;
    height: 100%;
  }
</style>
