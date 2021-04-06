<template>
  <div id="yardLegend">
    <div class="row" v-for="(item, index) in sta" :key="item.content">
      <div class="box" :class="`box-${itemColor(index)}`"></div>
      <div class="text">{{ item.content }}</div>
    </div>
  </div>
</template>

<script lang="ts">
import { Component } from "vue-property-decorator";
import Vue from "vue";
import { mapState } from "vuex";

@Component({
  name: "yardLegend",
  computed: {
    ...mapState({
      sta: (state: any) => state.yard.sta,
    }),
  },
})
export default class yardLegend extends Vue {
  //data
  private typeList = ["green", "red", "blue"];
  public sta!: any[];

  //methods
  itemColor(index: number) {
    return this.typeList[index];
  }
}
</script>

<style scoped lang="scss">
#yardLegend {
  position: absolute;
  top: 20vh;
  width: 80vw;
  margin: 0 10vw;
  background-color: #fff;
  border-radius: 5px;
  border-color: rgba(0, 0, 0, 0.15);
  border-style: outset;
  z-index: 999;
  padding: 10px 10px 0 10px;
  .row {
    display: flex;
    align-items: center;
    margin-bottom: 10px;
    height: 50px;
    * {
      width: 50%;
      height: 100%;
    }
    .box-green {
      background-color: #71b253;
    }
    .box-red {
      background-color: #e59090;
    }
    .box-blue {
      background-color: #58a4ce;
    }
    .text {
      line-height: 50px;
      font-weight: bold;
      font-size: 1.1rem;
      text-align: center;
    }
  }
}
</style>
