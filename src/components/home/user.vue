<template>
  <div id="user">
    <header>
      <v-avatar size="80" color="grey lighten-3">
        <v-img :src="require('@/assets/avatar.png')"></v-img>
      </v-avatar>
      <div class="name">管理员 : admin</div>
    </header>
    <v-btn tile block>客户管理</v-btn>
    <v-btn tile block>人事管理</v-btn>
    <v-btn tile block>消息</v-btn>
    <v-btn tile block>设置</v-btn>
  </div>
</template>

<script lang="ts">
import { Component } from "vue-property-decorator";
import Vue from "vue";

@Component({
  name: "user",
})
export default class User extends Vue {}
</script>

<style scoped lang="scss">
#user {
  align-self: flex-start;
  width: 100vw;
  padding: 0 25px;
  header {
    display: flex;
    justify-content: space-around;
    height: 100px;
    align-items: center;
    .name {
      line-height: 100px;
      font-weight: bold;
      font-size: 1.5rem;
    }
  }
  .v-btn {
    margin: 40px 0 0 0;
    font-size: 1.1rem;
    font-weight: bold;
  }
}
</style>
