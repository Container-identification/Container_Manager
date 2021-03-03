import axios from "axios";
const config = require("@/config.json");
import Home from "@/views/home.vue";
import { MyAlert, History } from "vue/types/vue";

let history: Function = async (
  data: object,
  vm: Home,
  additonalVM: History
): Promise<void> => {
  vm.loading = true;
  vm.loadingText = "查询历史中";

  // await axios
  //   .post(config.serverAddress + "/searchHistory", data)
  //   .then((res) => {
  //     vm.loading = false;
  //     vm.loadingText = "";
  //     let info: responseData = res.data;
  //     //tmp
  //     console.log(info);
  //     if (info.code === "0000") {
  //       //一切正常
  //       //待补充
  //     } else {
  //       (vm.$refs.alert as MyAlert).showUp(2000, "error", info.message);
  //     }
  //   })
  //   .catch((err) => {
  //     vm.loading = false;
  //     vm.loadingText = "";
  //     (vm.$refs.alert as MyAlert).showUp(2000, "error", "未知错误");
  //   });

  setTimeout(() => {
    vm.loading = false;
    vm.loadingText = "";
    additonalVM.hasRes = true;
  }, 2000);
};

export default history;
