<template>
  <div id="message">
    <header>
      <v-icon @click="back">fa-angle-left</v-icon>
      <div class="text">我的企信</div>
      <v-icon>fa-plus-circle</v-icon>
    </header>
    <main>
      <v-list dense>
        <v-divider></v-divider>

        <template v-for="(item, index) in testData">
          <v-list-item :key="item.name">
            <v-list-item-avatar>
              <v-img :src="item.avatar"></v-img>
            </v-list-item-avatar>
            <v-list-item-content>
              <v-list-item-title>{{ item.name }}</v-list-item-title>
              <v-list-item-subtitle>{{ item.content }}</v-list-item-subtitle>
            </v-list-item-content>
            <v-list-item-action-text>{{ item.time }}</v-list-item-action-text>
          </v-list-item>

          <v-divider :key="index"></v-divider>
        </template>
      </v-list>
    </main>
  </div>
</template>

<script lang="ts">
import { Component } from "vue-property-decorator";
import Vue from "vue";

@Component({
  name: "message",
})
export default class MyMessage extends Vue {
  //data
  private testData = [
    {
      avatar: require("@/assets/userAvatar.png"),
      name: "admin",
      content:
        "testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttest",
      time: "22:10",
    },
    {
      avatar: require("@/assets/userAvatar.png"),
      name: "admin2",
      content: "test",
      time: "22:15",
    },
    {
      avatar: require("@/assets/userAvatar.png"),
      name: "admin3",
      content: "test",
      time: "22:15",
    },
    {
      avatar: require("@/assets/userAvatar.png"),
      name: "admin4",
      content: "test",
      time: "22:15",
    },
    {
      avatar: require("@/assets/userAvatar.png"),
      name: "admin5",
      content: "test",
      time: "22:15",
    },
    {
      avatar: require("@/assets/userAvatar.png"),
      name: "admin6",
      content: "test",
      time: "22:15",
    },
    {
      avatar: require("@/assets/userAvatar.png"),
      name: "admin7",
      content: "test",
      time: "22:15",
    },
    {
      avatar: require("@/assets/userAvatar.png"),
      name: "admin8",
      content: "test",
      time: "22:15",
    },
    {
      avatar: require("@/assets/userAvatar.png"),
      name: "admin9",
      content: "test",
      time: "22:15",
    },
    {
      avatar: require("@/assets/userAvatar.png"),
      name: "admin10",
      content: "test",
      time: "22:15",
    },
    {
      avatar: require("@/assets/userAvatar.png"),
      name: "admin11",
      content: "test",
      time: "22:15",
    },
  ];

  //methods
  back() {
    this.$router.back();
  }
}
</script>

<style scoped lang="scss">
#message {
  header {
    @include userHeader;
    position: fixed;
    top: 0;
  }
  main {
    margin-top: 6vh;
    overflow: scroll;
    height: 86vh;
    .v-list {
      hr {
        margin: 0;
      }
      .v-list-item {
        background-color: $homeBackground;
      }
    }
  }
}
</style>
