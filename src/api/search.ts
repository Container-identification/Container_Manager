import axios from "axios";
const config = require("@/config.json");
import Home from "@/views/home.vue";
import { MyAlert, Search } from "vue/types/vue";

let search: Function = async (
  data: object,
  vm: Home,
  additonalVM: Search
): Promise<void> => {
  vm.loading = true;
  vm.loadingText = "查询中";

  // await axios
  //   .post(config.serverAddress + "/logistics", data)
  //   .then((res) => {
  //     vm.loading = false;
  //     vm.loadingText = "";
  //     let info: responseData = res.data;
  //     console.log(info);
  //     if (info.code === "0000") {
  //       //一切正常
  //       //待补充

  //       //视图效果
  //       if (!additonalVM.upMoving) {
  //         additonalVM.upMoving = true;
  //         setTimeout(() => {
  //           additonalVM.infoFlag = true;
  //         }, 500);
  //       }
  //     } else {
  //       (vm.$refs.alert as MyAlert).showUp(2000, "error", info.message);
  //     }
  //   })
  //   .catch((err) => {
  //     vm.loading = false;
  //     vm.loadingText = "";
  //     (vm.$refs.alert as MyAlert).showUp(2000, "error", "未知错误");
  //   });

  //tmp
  setTimeout(() => {
    vm.loading = false;
    vm.loadingText = "";
    vm.$router.push({ name: "info" });
  }, 2000);
};

export default search;
