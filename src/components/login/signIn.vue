<template>
  <div id="signIn">
    <v-text-field label="用户名" v-model="user" :rules="[valid1]">
      <template #prepend>
        <v-icon color="blue">fa-user-o</v-icon>
      </template>
    </v-text-field>
    <v-text-field
      label="密码"
      v-model="password"
      :type="show ? 'text' : 'password'"
      :append-icon="show ? 'fa-eye' : 'fa-eye-slash'"
      :rules="[valid2]"
      @click:append="show = !show"
    >
      <template #prepend>
        <v-icon color="blue">fa-key</v-icon>
      </template>
    </v-text-field>
    <v-btn
      class="action"
      @click="action"
      :disabled="checkList.some((val) => val)"
      block
      >登录</v-btn
    >
    <v-checkbox v-model="savePwd" label="是否记住密码"></v-checkbox>
  </div>
</template>

<script lang="ts">
import { Component } from "vue-property-decorator";
import Vue from "vue";
import vaildFunction from "@/utils/vaildFunction";
import plusExtends from "@/plugins/plusExtends";

@Component({
  name: "signIn",
})
export default class SignIn extends Vue {
  //data
  private user = "";
  private password = "";
  private show = false;
  private checkList = [true, true];
  public savePwd = true;

  //methods
  action(): void {
    let data = {
      username: this.user,
      password: this.password,
    };
    this.$api.signIn(data, this, this.$parent);
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

  //lifeCycle
  created() {
    plusExtends(() => {
      this.user = plus.storage.getItem("user");
      this.password = plus.storage.getItem("password");
    });
  }
}
</script>

<style scoped lang="scss">
#signIn {
  padding: 20px;
  .action {
    margin: 20px auto 0 auto;
    color: white;
    border-radius: 40px;
    background: linear-gradient(to right, #039be5, #b3e5fc);
  }
}
</style>
