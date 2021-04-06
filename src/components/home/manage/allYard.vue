<template>
  <div id="allYard">
    <div class="allWarp">
      <div class="row" v-for="item in rowNum" :key="item">
        <div
          class="box"
          v-for="i in colNum"
          :key="i"
          @click="toOneYard((item - 1) * 3 + i - 1)"
        >
          <!-- item为行数，每行3个分区，col为列数 -->
          <div class="warp">
            <div>第{{ (item - 1) * 3 + i }}分区</div>
            <div>{{ (item - 1) * 8 + 1 }}~{{ item * 8 }}行</div>
            <!-- 每行行数一样，只和item相关 -->
            <div>{{ (i - 1) * 5 + 1 }}~{{ i * 5 }}列</div>
            <!-- 每行列数只和i相关 -->
          </div>
        </div>
      </div>
      <!-- 最后一行 -->
      <div class="row">
        <div
          class="box"
          v-for="i in remainder"
          :key="i"
          @click="toOneYard(rowNum * 3 + i - 1)"
        >
          <div class="warp">
            <div>第{{ rowNum * 3 + i }}分区</div>
            <div>{{ rowNum * 8 + 1 }}~{{ (rowNum + 1) * 8 + 1 }}行</div>
            <div>{{ (i - 1) * 5 + 1 }}~{{ i * 5 }}列</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Component } from "vue-property-decorator";
import Vue from "vue";

@Component({
  name: "allYard",
  props: {
    yardNum: {
      type: Number,
      required: true,
    },
  },
})
export default class AllYard extends Vue {
  //data
  public yardNum!: number;
  private colNum: number = 3;
  private rowNum: number = Math.floor(this.yardNum / this.colNum);
  private remainder: number = this.yardNum % this.colNum;

  //methods
  toOneYard(index: number) {
    this.$store.commit("yard/setIndex", index);
    this.$store.commit("yard/changeView");
  }
}
</script>

<style scoped lang="scss">
#allYard {
  padding: 20px 20px 0 20px;
  height: 62.5vh;
  .allWarp {
    box-shadow: $contentShadow;
    background-color: #fff;
    .row {
      display: flex;
      .box {
        flex-grow: 1;
        height: 110px;
        border-color: rgba(0, 0, 0, 0.15);
        border-style: outset;
        display: flex;
        align-items: center;
        justify-content: center;
        .warp {
          text-align: center;
          font-weight: bold;
          font-size: 1.2rem;
        }
      }
    }
  }
}
</style>
