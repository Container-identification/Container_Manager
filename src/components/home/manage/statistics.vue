<template>
  <div id="statistics">
    <header>
      <div class="text">堆场统计</div>
      <div class="warp">
        <!-- <v-btn fab elevation="1">
          <v-icon>fa-file-text-o</v-icon>
        </v-btn> -->
      </div>
    </header>
    <main>
      <div class="warp">
        <div class="total">总计 : {{ yardData.total }}</div>
        <ul class="list">
          <li v-for="item in yardData.sort" :key="item.name">
            {{ item.name }} : {{ item.count }}个
          </li>
        </ul>
        <div class="repair" v-for="item in yardData.other" :key="item.name">
          {{ item.name }} : {{ item.content }}
        </div>
      </div>

      <div class="WeightWarp">
        <div class="totalWeight">总库存(kg) : {{ yardData.totalWeight }}</div>
        <div class="usedWeight">
          已用库存(kg) : <span>{{ yardData.usedWeight }}</span>
        </div>
        <div class="leftWeight">
          剩余库存(kg) :
          <span>{{ yardData.totalWeight - yardData.usedWeight }}</span>
        </div>
      </div>
      <div ref="charts" class="charts">统计图区</div>
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
import { mapState } from "vuex";

@Component({
  name: "statistics",
  computed: {
    ...mapState({
      token: (state: any) => state.token,
    }),
  },
})
export default class Statistice extends Vue {
  //data
  public yardData: any = {
    total: "",
    sort: [],
    other: [],
    totalWeight: "",
    usedWeight: "",
  };
  public token!: string;

  //methods
  back() {
    this.$router.go(-1);
  }

  //lifeCycle
  created() {
    this.$api.yardStatistics(this, this.$parent.$parent, this.token);
  }
}
</script>

<style scoped lang="scss">
#statistics {
  position: absolute;
  z-index: 2;
  background-color: $homeBackground;
  top: 0;
  width: 100vw;
  height: 100vh;
  header {
    @include myHeader;
    .text {
      @include headerText;
      background-color: #9a62e6;
    }
    .warp {
      display: flex;
      justify-content: center;
      flex-grow: 1;
    }
  }
  main {
    height: 75vh;
    margin: 0 20px;
    padding: 15px 25px 0 25px;
    overflow: scroll;
    * {
      letter-spacing: 1px;
      font-size: 1.1rem;
    }
    .warp {
      padding: 10px;
      margin: 0 0 10px 0;
      @include contentWarp;
      .repair {
        font-weight: bold;
        margin: 10px 0 0 0;
      }
      .total {
        font-weight: bold;
      }
    }
    .WeightWarp {
      padding: 10px;
      @include contentWarp;
      .totalWeight {
        font-weight: bold;
      }
      .usedWeight {
        span {
          color: red;
        }
      }
      .leftWeight {
        span {
          color: green;
        }
      }
    }
    .charts {
      height: 240px;
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
