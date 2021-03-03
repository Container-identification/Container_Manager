<template>
  <div id="manage">
    <transition name="operate">
      <router-view></router-view>
    </transition>

    <header>
      <div class="text">堆场管理</div>
      <div class="warp">
        <v-icon
          x-large
          :color="warnIcon ? 'red' : 'grey'"
          :class="{ warnIcon: warnIcon }"
          @click="showWarnInfo"
          >fa-exclamation-triangle</v-icon
        >
        <v-icon x-large color="blue">fa-plus-circle</v-icon>
      </div>
    </header>

    <main>
      <!-- 第一行特殊值 -->
      <div class="row-header row">
        <!-- 第一个占位盒子 -->
        <div class="none-box box"></div>
        <!-- 第一行特殊值盒子 -->
        <div class="box" v-for="item in currentData[0].length" :key="item">
          {{ item + 100 }}
        </div>
      </div>
      <!-- 构建堆场 -->
      <div
        class="row"
        v-for="(item, row) in currentData"
        :key="item[0] + Math.random()"
        :class="{ ['row-odd']: row % 2 === 0 }"
      >
        <!-- 头部特殊盒子 -->
        <div class="none-box box">{{ row }}</div>
        <div
          class="box"
          v-for="(i, col) in item"
          :key="i + Math.random()"
          :class="{
            ['box-red']: i === 0,
            ['box-green']: i === 2,
            ['box-blue']: i === 3,
          }"
          @click="toOperate(i, row, col + 100, $event)"
        ></div>
      </div>
    </main>

    <div class="countInfo">
      <div class="total">堆场总个数:{{ yard.length }}</div>
      <div class="current">当前堆场编号:{{ pageData + 1 }}</div>
    </div>

    <footer>
      <v-btn @click="toLeft" fab elevation="1" large>
        <v-icon>fa-angle-left</v-icon>
      </v-btn>
      <v-btn fab large elevation="1" @click="toStatistics">
        <v-icon>fa-bar-chart</v-icon>
      </v-btn>
      <v-btn @click="toRight" fab elevation="1" large>
        <v-icon>fa-angle-right </v-icon>
      </v-btn>
    </footer>
  </div>
</template>

<script lang="ts">
import { Component } from "vue-property-decorator";
import Vue from "vue";
import { MyAlert } from "vue/types/vue";
import { mapState } from "vuex";

@Component({
  name: "manage",
  computed: {
    ...mapState({
      yard: (state: any) => state.yard.data,
    }),
  },
})
export default class Manage extends Vue {
  //data
  private pageData = 0;
  public yard!: [[number[]]];

  //methods
  toStatistics() {
    this.$router.push({ name: "statistics" });
  }
  toOperate(id: string, row: string, col: string, event: any) {
    this.$router.push({
      name: "operate",
      params: {
        id,
        index: this.pageData.toString(), //tmp
        row,
        col,
      },
    });
  }
  toLeft() {
    if (--this.pageData < 0) {
      this.pageData = this.yard.length - 1;
    }
  }
  toRight() {
    if (++this.pageData >= this.yard.length) {
      this.pageData = 0;
    }
  }
  showWarnInfo() {
    (this.$parent.$refs.alert as MyAlert).showUp(
      3000,
      "warning",
      "若此标志闪烁，说明当前堆场存在需要维修的货箱"
    );
  }

  //computed
  get currentData() {
    return this.yard[this.pageData];
  }
  get warnIcon() {
    //判断是否有待维修的货箱
    return this.currentData.some((arr) => {
      return arr.some((val) => val === 0);
    });
  }
}
</script>

<style scoped lang="scss">
#manage {
  height: 100%;
  width: 100%;
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
    .warp {
      padding-left: 20px;
      .v-icon {
        margin-right: 20px;
      }
      .warnIcon {
        animation-name: warnAnimate;
        animation-duration: 1s;
        animation-iteration-count: infinite;
        animation-timing-function: cubic-bezier(0.25, 0.8, 0.5, 1);
      }
      @keyframes warnAnimate {
        0% {
          opacity: 0;
        }
        50% {
          opacity: 1;
        }
        100% {
          opacity: 0;
        }
      }
    }
  }
  main {
    padding: 20px 20px 0 0;
    height: 62.5vh;
    // 偶数行
    .row-odd {
      .box {
        background-color: #e0e0e0;
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
  }
  .countInfo {
    margin: 20px 0;
    display: flex;
    justify-content: space-around;
    font-weight: bolder;
    font-size: 1.125rem;
  }
  footer {
    display: flex;
    justify-content: space-around;
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
