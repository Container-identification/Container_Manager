<template>
  <div id="index">
    <header>
      <v-avatar size="80" color="grey lighten-3">
        <v-img :src="require('@/assets/avatar.png')"></v-img>
      </v-avatar>
      <div class="warp">
        <div class="name">管理员 : admin</div>
        <div class="description">天津港码头集装箱01号仓库</div>
      </div>
    </header>
    <v-btn>客户管理</v-btn>
    <v-btn @click="redirect('hrManage')">人事管理</v-btn>
    <v-btn @click="redirect('message')">消息</v-btn>
    <v-btn>设置</v-btn>
  </div>
</template>

<script lang="ts">
import { Component } from "vue-property-decorator";
import Vue from "vue";

@Component({
  name: "index",
})
export default class UserIndex extends Vue {
  //methods
  redirect(name: string) {
    this.$router.push({
      name,
    });
  }
}
</script>

<style scoped lang="scss">
#index {
  header {
    display: grid;
    grid-template-columns: 1fr 1fr;
    height: 100px;
    place-items: center;
    .name {
      font-weight: bold;
      font-size: 1.5rem;
      margin-bottom: 10px;
    }
    .description {
      font-size: 0.85rem;
    }
  }
  .v-btn {
    margin: 40px 10vw 0 10vw;
    padding: 30px 0;
    width: 80vw;
    height: auto;
    font-size: 1.1rem;
    font-weight: bold;
    border-radius: 12px;
  }
}
</style>
