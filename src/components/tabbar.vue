<template>
  <div id="tabbar">
    <v-bottom-navigation
      color="light-blue darken-1"
      grow
      @change="redirect"
      :value="active"
    >
      <v-btn value="shot">
        <span>识别</span>
        <v-icon>fa-camera</v-icon>
      </v-btn>
      <v-btn value="manage">
        <span>管理</span>
        <v-icon>fa-th</v-icon>
      </v-btn>
      <v-btn value="search">
        <span>查询</span>
        <v-icon>fa-search</v-icon>
      </v-btn>
      <v-btn value="user">
        <span>我的</span>
        <v-icon>fa-user</v-icon>
      </v-btn>
    </v-bottom-navigation>
  </div>
</template>

<script lang="ts">
import { Component } from "vue-property-decorator";
import Vue from "vue";

@Component({
  name: "tabbar",
})
export default class Tabbar extends Vue {
  //data
  private active = "shot";

  //methods
  redirect(name: string) {
    this.active = name;
    this.$router.push({ name: name });
  }
}
</script>

<style scoped lang="scss">
#tabbar {
  position: absolute;
  bottom: 0;
  width: 100vw;
  z-index: 1;
  min-height: 56px;
  // height: 8vh;
}
</style>
