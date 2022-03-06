<template>
  <b-carousel :interval="3000" controls indicators>
    <b-carousel-slide>
      <template #img>
        <b-img :src="require('@/assets/banner/banner1.jpg')" width="1535" height="650" />
      </template>
      <h1>Bee Happy에서 원하는 집을 찾으세요</h1>
    </b-carousel-slide>

    <b-carousel-slide>
      <template #img>
        <b-img :src="require('@/assets/banner/banner4.jpg')" width="1535" height="650" />
      </template>
      <h1>Bee Happy에서 원하는 집을 찾으세요</h1>
    </b-carousel-slide>

    <b-carousel-slide>
      <template #img>
        <b-img :src="require('@/assets/banner/banner2.jpg')" width="1535" height="650" />
      </template>
      <h1>Bee Happy에서 원하는 집을 찾으세요</h1>
    </b-carousel-slide>

    <b-carousel-slide>
      <template #img>
        <b-img :src="require('@/assets/banner/banner5.jpg')" width="1535" height="650" />
      </template>
      <h1>Bee Happy에서 원하는 집을 찾으세요</h1>
    </b-carousel-slide>

    <b-carousel-slide>
      <template #img>
        <b-img :src="require('@/assets/banner/banner3.jpg')" width="1535" height="650" />
      </template>
      <h1>Bee Happy에서 원하는 집을 찾으세요</h1>
    </b-carousel-slide>
  </b-carousel>
</template>

<script>
  export default {};
</script>
