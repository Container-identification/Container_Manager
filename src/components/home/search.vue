<template>
  <div id="search">
    <header>
      <div class="text">箱号查询</div>
      <div class="warp">
        <v-btn @click="clearHistory" fab elevation="1"
          ><v-icon>fa-times</v-icon></v-btn
        >
      </div>
    </header>

    <v-text-field
      label="搜索指定货箱号"
      prepend-icon="fa-search"
      v-model="container"
    >
      <template #append-outer>
        <v-btn @click="search">查询</v-btn>
      </template>
    </v-text-field>

    <v-img v-if="isShowImg" :src="containerImg" max-width="80vw"></v-img>

    <div class="history">
      <div class="text">历史查询箱号</div>
      <ul>
        <transition-group name="infoShow" appear>
          <li v-for="item in historys" :key="item">{{ item }}</li>
        </transition-group>
      </ul>
    </div>
  </div>
</template>

<script lang="ts">
import { Component } from "vue-property-decorator";
import Vue from "vue";
import { mapState } from "vuex";
import { Home } from "vue/types/vue";

@Component({
  name: "search",
  computed: {
    ...mapState({
      token: (state: any) => state.token,
      historys: (state: any) => state.history.list,
    }),
  },
})
export default class Search extends Vue {
  //data
  private container: string = "";
  private containerImg: string = "";

  //methods
  search() {
    let data = {
      token: this.token,
      container: this.container,
    };
    this.$api.search(data, this, this.$parent, true);
    this.$store.commit("history/addHistory", data.container);
  }
  clearHistory() {
    this.$store.commit("history/clearHistory");
    (this.$parent as Home).$refs.alert.showUp(2000, "success", "清除成功");
  }

  //computed
  get isShowImg() {
    return this.containerImg != undefined && this.containerImg !== "";
  }

  //lifeCycle
  created() {
    //获取识别的箱号
    this.container = this.$route.params.container;
    this.containerImg = this.$route.params.containerImg;
  }
}
</script>

<style scoped lang="scss">
#search {
  align-self: flex-start;
  header {
    width: 100vw;
    @include myHeader;
    .text {
      @include headerText;
      background-color: #dce382;
    }
    .warp {
      flex-grow: 1;
      display: flex;
      justify-content: flex-end;
    }
  }
  .v-input {
    width: 80vw;
    margin: 10px auto 0 auto;
  }
  .v-image {
    margin: 10px auto;
    @include contentWarp;
  }
  .infoShow-enter-active {
    animation-name: fadeInUp;
    animation-duration: 1s;
  }
  .history {
    width: 80vw;
    margin: 0 auto;
    padding: 10px;
    @include contentWarp;
    .text {
      font-size: 1.25rem;
      letter-spacing: 1px;
    }
  }
}
</style>
