<template>
  <div id="user">
    <router-view></router-view>
  </div>
</template>

<script lang="ts">
import { Component } from "vue-property-decorator";
import Vue from "vue";

@Component({
  name: "user",
})
export default class User extends Vue {
  //lifeCycle
  mounted() {
    this.$router.push({ name: "userIndex" });
  }
}
</script>

<style scoped lang="scss">
#user {
  align-self: flex-start;
  width: 100vw;
  height: 100%;
}
</style>
