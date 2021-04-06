<template>
  <div id="manage">
    <header>
      <div class="text">堆场管理</div>
      <div class="warp">
        <v-btn
          @click="showYardLegend"
          fab
          elevation="1"
          :disabled="isShowYardLegend"
          ><v-icon large color="black">fa-bars</v-icon></v-btn
        >
        <v-btn v-if="!isAllYard" fab elevation="1" @click="showWarnInfo">
          <v-icon
            large
            :color="warnIcon ? 'red' : 'grey'"
            :class="{ warnIcon: warnIcon }"
            >fa-exclamation-triangle</v-icon
          >
        </v-btn>
        <v-btn fab elevation="1"
          ><v-icon large color="blue">fa-plus</v-icon></v-btn
        >
      </div>
    </header>

    <!-- <transition name="yard" mode="out-in"> -->
    <transition name="allYard">
      <all-yard :yardNum="yard.length" v-if="isAllYard"></all-yard>
    </transition>
    <transition name="singleYard">
      <single-yard
        :yard="currentData"
        :pageData="yardIndex"
        v-if="!isAllYard"
      ></single-yard>
    </transition>
    <!-- </transition> -->

    <div v-if="!isAllYard" class="countInfo">
      <div class="total">总分区个数:{{ yard.length }}</div>
      <div class="current">当前分区编号:{{ yardIndex + 1 }}</div>
    </div>
    <div v-else class="countInfoPlace"></div>

    <footer>
      <v-btn @click="toAllYard" fab elevation="1" large v-if="!isAllYard">
        <v-icon>fa-reply</v-icon>
      </v-btn>
      <v-btn fab large elevation="1" @click="toStatistics">
        <v-icon>fa-bar-chart</v-icon>
      </v-btn>
    </footer>

    <transition name="yardLegend">
      <yard-legend v-if="isShowYardLegend"></yard-legend>
    </transition>

    <transition name="statistics">
      <router-view></router-view>
    </transition>
  </div>
</template>

<script lang="ts">
import { Component } from "vue-property-decorator";
import Vue from "vue";
import { Home } from "vue/types/vue";
import { mapState } from "vuex";
import SingleYard from "@/components/home/manage/singleYard.vue";
import AllYard from "@/components/home/manage/allYard.vue";
import yardLegend from "@/components/home/manage/yardLegend.vue";
import { containerInfo } from "@/store/module";

@Component({
  name: "manage",
  computed: {
    ...mapState({
      yard: (state: any) => state.yard.yardArray,
      isAllYard: (state: any) => state.yard.isAllYard,
      yardIndex: (state: any) => state.yard.yardIndex,
    }),
  },
  components: {
    SingleYard,
    AllYard,
    yardLegend,
  },
})
export default class Manage extends Vue {
  //data
  public yard!: [[(containerInfo | undefined)[]]];
  public yardIndex!: number;
  private isShowYardLegend: boolean = false;

  //methods
  toStatistics() {
    this.$router.push({ name: "statistics" });
  }
  showWarnInfo() {
    (this.$parent as Home).$refs.alert.showUp(
      3000,
      "warning",
      "若此标志闪烁，说明当前堆场存在需要维修的货箱"
    );
  }
  toAllYard() {
    this.$store.commit("yard/changeView");
  }
  showYardLegend() {
    this.isShowYardLegend = true;
    setTimeout(() => {
      this.isShowYardLegend = false;
    }, 2000);
  }

  //computed
  get currentData() {
    return this.yard[this.yardIndex];
  }
  get warnIcon() {
    //判断是否有待维修的货箱
    return this.currentData.some((arr) => {
      return arr.some((val) => val && val.sta == "1");
    });
  }
}
</script>

<style scoped lang="scss">
#manage {
  height: 100%;
  width: 100%;
  header {
    @include myHeader;
    .text {
      @include headerText;
      background-color: #91a0b6;
    }
    .warp {
      display: flex;
      justify-content: flex-end;
      flex-grow: 1;
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
  .countInfo {
    margin: 20px 0;
    display: flex;
    justify-content: space-around;
    font-weight: bolder;
    font-size: 1.125rem;
  }
  .countInfoPlace {
    margin: 20px 0;
    height: 26.4px;
  }
  footer {
    display: flex;
    justify-content: space-around;
  }
  .yardLegend-enter-active {
    animation-name: fadeInDown;
    animation-duration: 0.4s;
  }
  .yardLegend-leave-active {
    animation-name: fadeOutUp;
    animation-duration: 0.4s;
  }
  .singleYard-enter-active {
    animation-name: zoomIn;
    animation-duration: 0.5s;
  }
  .allYard-enter-active {
    animation-name: fadeIn;
    animation-duration: 0.5s;
  }
  .statistics-enter-active {
    animation-name: fadeInDown;
    animation-duration: 0.4s;
  }
  .statistics-leave-active {
    animation-name: fadeOutUp;
    animation-duration: 0.4s;
  }
}
</style>
