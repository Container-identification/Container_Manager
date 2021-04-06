<template>
  <div id="hrManage">
    <header>
      <v-icon @click="back">fa-angle-left</v-icon>
      <div class="text">人事管理</div>
      <div></div>
    </header>
    <main>
      <v-text-field label="搜索" prepend-icon="fa-search" v-model="search">
        <template #append-outer>
          <v-btn>查询</v-btn>
        </template>
      </v-text-field>

      <v-list>
        <v-list-group v-for="item in testData" :key="item.name">
          <template #activator>
            <v-list-item-content>
              <v-list-item-title>{{
                `${item.name}(${item.data.length}人)`
              }}</v-list-item-title>
            </v-list-item-content>
          </template>

          <v-list-item v-for="i in item.data" :key="i.name">
            <v-list-item-avatar>
              <v-img :src="i.avatar"></v-img>
            </v-list-item-avatar>
            <v-list-item-content>{{ i.name }}</v-list-item-content>
          </v-list-item>
        </v-list-group>
      </v-list>
    </main>
  </div>
</template>

<script lang="ts">
import { Component } from "vue-property-decorator";
import Vue from "vue";

@Component({
  name: "hrManage",
})
export default class HRManage extends Vue {
  //data
  private search: string = "";
  private testData = [
    {
      name: "商务部",
      data: [
        {
          avatar: require("@/assets/userAvatar.png"),
          name: "商务部-小李",
        },
        {
          avatar: require("@/assets/userAvatar.png"),
          name: "商务部-小张",
        },
        {
          avatar: require("@/assets/userAvatar.png"),
          name: "商务部-小王",
        },
      ],
    },
    {
      name: "技术部",
      data: [
        {
          avatar: require("@/assets/userAvatar.png"),
          name: "技术部-小雪",
        },
        {
          avatar: require("@/assets/userAvatar.png"),
          name: "技术部-老张",
        },
        {
          avatar: require("@/assets/userAvatar.png"),
          name: "技术部-老王",
        },
      ],
    },
  ];
  private activeIndex: number = 1;

  //methods
  back() {
    this.$router.back();
  }
}
</script>

<style scoped lang="scss">
#hrManage {
  header {
    @include userHeader;
    position: fixed;
    top: 0;
  }
  main {
    margin-top: 6vh;
    height: 86vh;
    overflow: scroll;
    .v-input {
      width: 80vw;
      margin-left: auto;
      margin-right: auto;
    }
  }
}
</style>
