<template>
  <div id="singleYard">
    <!-- 第一行特殊值 -->
    <div class="row-header row">
      <!-- 第一个占位盒子 -->
      <div class="none-box box"></div>
      <!-- 第一行特殊值盒子 -->
      <div class="box" v-for="item in yard[0].length" :key="item">
        {{ item + (pageData % 3) * 5 }}
        <!-- col为3，pageData%3获取每行的第几列，每个分区有5列 -->
      </div>
    </div>
    <!-- 构建堆场 -->
    <div
      class="row"
      v-for="(item, row) in yard"
      :key="row"
      :class="{ ['row-even']: row % 2 === 0, ['row-odd']: row % 2 !== 0 }"
    >
      <!-- 头部特殊盒子 -->
      <div class="none-box box">
        {{ row + 1 + Math.floor(pageData / 3) * 8 }}
        <!-- row是index，pageData/3为第几行，每个分区有8行 -->
      </div>
      <div
        class="box"
        v-for="(i, col) in item"
        :key="i ? i.sta + Math.random() : Math.random()"
        :class="{
          ['box-red']: i && i.sta == 1,
          ['box-green']: i && i.sta == 0,
          ['box-blue']: i && i.sta == 2,
        }"
        @click="toOperate(row, col)"
      ></div>
    </div>

    <transition name="operate">
      <operate
        :containerInfo="operateInfo"
        :locate="locate"
        v-if="isShowOperate"
      ></operate>
    </transition>
  </div>
</template>

<script lang="ts">
import { Component } from "vue-property-decorator";
import Vue from "vue";
import Operate from "@/components/home/manage/operate.vue";
import { containerInfo } from "@/store/module";

@Component({
  name: "singleYard",
  props: {
    yard: {
      type: Array,
      required: true,
    },
    pageData: {
      type: Number,
      required: true,
    },
  },
  components: {
    Operate,
  },
})
export default class SingleYard extends Vue {
  //data
  public pageData!: number;
  public yard!: [];
  private isShowOperate = false;
  public operateInfo: containerInfo | undefined;
  public locate: any;

  //methods
  toOperate(row: number, col: number) {
    this.locate = {
      pageData: this.pageData,
      row,
      col,
    };
    this.operateInfo = this.yard[row][col];
    this.isShowOperate = true;
  }
  closeOperate() {
    this.isShowOperate = false;
  }

  //lifeCycle
  created() {
    console.log(this.yard);
  }
}
</script>

<style scoped lang="scss">
#singleYard {
  padding: 20px 20px 0 0;
  height: 62.5vh;
  overflow: scroll;
  // 偶数行
  .row-even {
    .box {
      background-color: #e0e0e0;
    }
  }
  // 奇数行
  .row-odd {
    .box {
      background-color: #fff;
    }
  }
  // 每一行
  .row {
    display: flex;
    // 普通盒子
    .box {
      height: 50px;
      width: 50px;
      line-height: 50px;
      border-color: rgba(0, 0, 0, 0.15);
      border-style: outset;
      flex-grow: 1;
      text-align: center;
    }
    // 头部特殊盒子
    .none-box {
      width: 25px;
      border: none;
      background-color: unset;
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
  }
  // 第一行盒子
  .row-header {
    .box {
      border: none;
    }
  }
  .operate-enter-active {
    animation-name: fadeInDown;
    animation-duration: 0.5s;
  }
  .operate-leave-active {
    animation-name: fadeOutDown;
    animation-duration: 0.5s;
  }
}
</style>
