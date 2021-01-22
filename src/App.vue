<template>
  <v-app>
    <v-main>
      <transition name="begin" :appear="true">
        <router-view></router-view>
      </transition>
    </v-main>
  </v-app>
</template>

<script lang="ts">
import Vue from "vue";

export default Vue.extend({
  name: "App",
});
</script>

<style lang="scss" scoped>
#app {
  height: 100vh;
  .begin-enter-active {
    animation-name: fadeIn;
    animation-duration: 0.4s;
  }
}
</style>
