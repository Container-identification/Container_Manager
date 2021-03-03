<template>
  <div id="login">
    <header>用户登录</header>
    <main>
      <transition name="toggle" mode="out-in">
        <sign-in v-if="isSignIn"></sign-in>
        <sign-up v-else></sign-up>
      </transition>
    </main>
    <!-- 注册暂时停用 -->
    <!-- <div class="toggle">
      <span>{{ tip }}</span>
      <v-btn @click="change(isSignIn)">{{ action }}</v-btn>
    </div> -->
    <loading :isShow="loading" :text="loadingText"></loading>
    <my-alert ref="alert"></my-alert>
  </div>
</template>

<script lang="ts">
import { Component } from "vue-property-decorator";
import Vue from "vue";
import SignIn from "@/components/login/signIn.vue";
import SignUp from "@/components/login/signUp.vue";
import loading from "@/components/loading.vue";
import myAlert from "@/components/myAlert.vue";

@Component({
  name: "login",
  components: {
    SignIn,
    SignUp,
    loading,
    myAlert,
  },
})
export default class Login extends Vue {
  //data
  isSignIn = true;
  tip = "没有账号?";
  action = "去注册";
  loading = false;
  loadingText = "正在登录";

  //methods
  change(isSignIn: Boolean) {
    if (isSignIn) {
      //要切换到注册页面
      this.tip = "已有账号!";
      this.action = "去登录";
      this.loadingText = "正在注册";
    } else {
      this.tip = "没有账号?";
      this.action = "去注册";
      this.loadingText = "正在登录";
    }
    this.isSignIn = !this.isSignIn;
  }
}
</script>

<style scoped lang="scss">
#login {
  height: 100%;
  padding: 100px 30px 0 30px;
  header {
    position: absolute;
    left: 5vw;
    top: 1vh;
    font-weight: bolder;
    font-size: 1.5rem;
  }
  main {
    height: 300px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    .toggle-enter-active {
      animation-name: fadeInRight;
      animation-duration: 0.5s;
    }
    .toggle-leave-active {
      animation-name: fadeOutLeft;
      animation-duration: 0.2s;
    }
  }
  .toggle {
    display: flex;
    flex-direction: column;
    align-items: center;
    span {
      margin-bottom: 6px;
      letter-spacing: 1px;
    }
  }
}
</style>
