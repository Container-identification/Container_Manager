<template>
  <div id="signIn">
    <v-text-field
      label="用户名"
      prepend-icon="fa-user-o"
      v-model="user"
      :rules="[valid1]"
    ></v-text-field>
    <v-text-field
      label="密码"
      prepend-icon="fa-key"
      v-model="password"
      :type="show ? 'text' : 'password'"
      :append-icon="show ? 'fa-eye' : 'fa-eye-slash'"
      :rules="[valid2]"
      @click:append="show = !show"
    ></v-text-field>
    <v-btn
      class="action"
      @click="action"
      :disabled="checkList.some((val) => val)"
      color="blue"
      >登录</v-btn
    >
  </div>
</template>

<script lang="ts">
import { Component } from "vue-property-decorator";
import Vue from "vue";
import vaildFunction from "@/utils/vaildFunction";

@Component({
  name: "signIn",
})
export default class SignIn extends Vue {
  //data
  user = "admin";
  password = "12345678";
  show = false;
  checkList = [true, true];

  //methods
  action(): void {
    let data = {
      user: this.user,
      password: this.password,
    };
    this.$api.signIn(data, this.$parent);
  }
  valid1(value: string | number | undefined): boolean | string {
    if (vaildFunction.isNothing(value)) {
      this.$set(this.checkList, 0, true);
      return "不能为空";
    }
    this.$set(this.checkList, 0, false);
    return true;
  }
  valid2(value: string | number | undefined): boolean | string {
    if (vaildFunction.isNothing(value)) {
      this.$set(this.checkList, 1, true);
      return "不能为空";
    }
    this.$set(this.checkList, 1, false);
    return true;
  }
}
</script>

<style scoped lang="scss">
#signIn {
  border: 1px solid black;
  border-radius: 20px;
  padding: 20px;
  .action {
    display: block;
    margin: 20px auto 0 auto;
    color: white;
  }
}
</style>
