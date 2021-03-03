import Login from "@/views/login.vue";
import axios from "axios";
import { MyAlert } from "vue/types/vue";
const config = require("../config.json");

let signIn: Function = async (data: object, vm: Login): Promise<void> => {
  vm.loading = true;

  // await axios
  //   .post(config.serverAddress + "/login", data)
  //   .then((res) => {
  //     let info: responseData = res.data;
  //     console.log(info);
  //     console.log("token自动设为123456");

  //     //临时测试
  //     setTimeout(() => {
  //       vm.$store.commit("setToken", "123456");
  //       vm.loading = false;
  //       vm.$router.push({ name: "home" });
  //     }, 2000);

  //     // //一切正常
  //     // if (info.code === "0000") {
  //     //   //保存token
  //     //   vm.$store.commit("setToken", info.data.token);
  //     //   vm.loading = false;
  //     //   vm.$router.push({ name: "home" });
  //     // } else {
  //     //   //报错
  //     //   vm.loading = false;
  //     //   (vm.$refs.alert as MyAlert).showUp(2000, "error", info.message);
  //     // }
  //   })
  //   .catch((err) => {
  //     console.log(err);
  //     vm.loading = false;
  //     (vm.$refs.alert as MyAlert).showUp(3000, "error", "未知错误!");
  //   });

  //临时测试
  setTimeout(() => {
    vm.$store.commit("setToken", "123456");
    vm.loading = false;
    vm.$router.push({ name: "home" });
  }, 2000);
};

export default signIn;
