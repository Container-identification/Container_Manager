<template>
  <div id="home">
    <transition name="toggle" :appear="true">
      <router-view></router-view>
    </transition>
    <loading
      :isShow="loading"
      :text="loadingText"
      :imgUrl="loadingImg"
    ></loading>
    <my-alert ref="alert"></my-alert>
    <tabbar ref="tabbar"></tabbar>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import loading from "@/components/loading.vue";
import MyAlert from "@/components/myAlert.vue";
import Tabbar from "@/components/tabbar.vue";
import { mapState } from "vuex";

@Component({
  name: "home",
  components: {
    loading,
    MyAlert,
    Tabbar,
  },
  computed: {
    ...mapState({
      token: (state: any) => state.token,
    }),
  },
})
export default class Home extends Vue {
  //data
  public loadingText: String = "";
  public loading: Boolean = false;
  public loadingImg: string = "";
  public token!: string;

  //lifeCycle
  mounted() {
    this.$router.push({ name: "shot" });
    this.$api.queryYard({ token: this.token }, this);
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
  background-color: $homeBackground;
  background-image: url("../assets/homeBack.jpg");
  background-size: cover;
  .toggle-enter-active {
    animation-name: fadeIn;
    animation-duration: 0.4s;
  }
}
</style>
