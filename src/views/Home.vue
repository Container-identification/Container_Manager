<template>
  <div id="home">
    <transition name="toggle" :appear="true">
      <router-view></router-view>
    </transition>
    <loading :isShow="loading" :text="loadingText"></loading>
    <my-alert ref="alert"></my-alert>
    <tabbar ref="tabbar"></tabbar>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import loading from "@/components/loading.vue";
import MyAlert from "@/components/myAlert.vue";
import Tabbar from "@/components/tabbar.vue";

@Component({
  name: "home",
  components: {
    loading,
    MyAlert,
    Tabbar,
  },
})
export default class Home extends Vue {
  //data
  loadingText: String = "";
  loading: Boolean = false;

  //lifeCycle
  mounted() {
    this.$router.push({ name: "shot" });
  }
}
</script>

<style lang="scss" scoped>
#home {
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  overflow-y: scroll;
  .toggle-enter-active {
    animation-name: fadeIn;
    animation-duration: 0.4s;
  }
}
</style>
