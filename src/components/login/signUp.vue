<template>
  <div id="signUp">
    <v-text-field
      label="用户名"
      prepend-icon="fa-user-o"
      :rules="[validNone]"
    ></v-text-field>
    <v-text-field
      label="密码"
      prepend-icon="fa-key"
      :type="show1 ? 'text' : 'password'"
      :append-icon="show1 ? 'fa-eye' : 'fa-eye-slash'"
      @click:append="show1 = !show1"
      :rules="[validNone]"
    ></v-text-field>
    <v-text-field
      label="再次输入密码"
      prepend-icon="fa-key"
      :type="show2 ? 'text' : 'password'"
      :append-icon="show2 ? 'fa-eye' : 'fa-eye-slash'"
      @click:append="show2 = !show2"
      :rules="[validNone]"
    ></v-text-field>
  </div>
</template>

<script lang="ts">
import { Component } from "vue-property-decorator";
import Vue from "vue";

@Component({
  name: "SignUp",
})
export default class SignUp extends Vue {
  //data
  show1 = false;
  show2 = false;
  check = true;

  //methods
  validNone(value: string | number | undefined): boolean | string {
    if (value == undefined || value == "") {
      this.check = true;
      return "不能为空";
    }
    this.check = false;
    return true;
  }
}
</script>

<style scoped lang="scss">
#signUp {
  border: 1px solid black;
  border-radius: 20px;
  padding: 20px;
}
</style>
