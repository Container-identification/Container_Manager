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
import plusExtends from "@/plugins/plusExtends";

export default Vue.extend({
  //lifeCycle
  created() {
    //路由控制
    this.$router.push({ name: "login" });
    // this.$router.push({ name: "home" });
    // this.$router.push({ name: "info" });

    //history获取
    plusExtends(() => {
      this.$store.commit("history/init");
    });
  },
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
