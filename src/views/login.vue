<template>
  <div id="login">
    <img class="back1" :src="require('@/assets/loginBack1.png')" />
    <img class="back2" :src="require('@/assets/loginBack2.png')" />
    <header>
      <div class="myTitle">登录</div>
      <div class="subtitle">欢迎再次回来~</div>
    </header>
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
  padding: 28.5vh 30px 0 30px;
  img {
    position: absolute;
    top: 0;
    right: 0;
  }
  .back1 {
    width: 35vw;
  }
  .back2 {
    width: 45vw;
  }
  header {
    position: absolute;
    left: 10vw;
    top: 14vh;
    .myTitle {
      font-size: 1.5rem;
      font-weight: bold;
      letter-spacing: 0.5rem;
    }
    .subtitle {
      letter-spacing: 1px;
      color: #757575;
    }
  }
  main {
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
