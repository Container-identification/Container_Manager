<template>
  <div id="statistics">
    <header>
      <div class="text">堆场统计页面</div>
      <v-icon>fa-file-text-o</v-icon>
    </header>
    <main>
      <div class="total">总计 : {{ testData.total }}</div>
      <ul class="list">
        <li v-for="item in testData.list" :key="item.name">
          {{ item.name }} : {{ item.num }}个
        </li>
      </ul>
      <div class="nothing">空回区 : {{ testData.nothing }}</div>
      <div class="reback">重回区 : {{ testData.reback }}</div>
      <div class="repair">需要维护的货箱个数 : {{ testData.repair }}</div>
      <div class="totalKg">总库存(kg) : {{ testData.totalKg }}</div>
      <div class="leftKg">剩余库存(kg) : {{ testData.leftKg }}</div>
      <v-img
        height="150px"
        contain
        :src="require('@/assets/statistics.png')"
      ></v-img>
    </main>
    <footer>
      <v-btn fab elevation="1"><v-icon>fa-pencil</v-icon></v-btn>
      <v-btn fab elevation="1" @click="back">
        <v-icon>fa-reply</v-icon>
      </v-btn>
      <v-btn fab elevation="1"><v-icon>fa-book</v-icon></v-btn>
    </footer>
  </div>
</template>

<script lang="ts">
import { Component } from "vue-property-decorator";
import Vue from "vue";

@Component({
  name: "statistics",
})
export default class Statistice extends Vue {
  //data
  testData = {
    total: 104,
    list: [
      {
        name: "20英寸开顶箱",
        num: 46,
      },
      {
        name: "20英寸冷冻箱",
        num: 25,
      },
      {
        name: "40英寸干货箱",
        num: 21,
      },
      {
        name: "40英寸冷冻箱",
        num: 9,
      },
      {
        name: "45英寸冷高箱",
        num: 3,
      },
    ],
    nothing: 0,
    reback: 0,
    repair: 2,
    totalKg: 1004,
    leftKg: 570,
  };

  //methods
  back() {
    this.$router.go(-1);
  }
}
</script>

<style scoped lang="scss">
#statistics {
  position: absolute;
  z-index: 2;
  background-color: #fff;
  width: 100vw;
  height: 100vh;
  header {
    height: 8vh;
    display: flex;
    align-items: center;
    justify-content: space-between;
    .text {
      text-align: center;
      font-weight: bolder;
      font-size: 1.5rem;
      line-height: 8vh;
      flex-grow: 1;
      height: inherit;
    }
    i {
      margin: 0 20px;
      flex-grow: 1;
    }
  }
  main {
    outline: 1px solid black;
    height: 70vh;
    margin: 20px 20px 0 20px;
    padding: 30px 25px 0 25px;
    * {
      letter-spacing: 1px;
      font-size: 1.1rem;
    }
    .total,
    .totalKg {
      font-weight: bold;
    }
    .repair {
      margin-bottom: 30px;
    }
    .v-image {
      margin-top: 10px;
    }
  }
  footer {
    position: absolute;
    bottom: 6vh;
    display: flex;
    width: 100vw;
    justify-content: space-around;
  }
}
</style>
