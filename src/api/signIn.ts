import axios from "axios";
import { Login } from "vue/types/vue";
import plusExtends from "@/plugins/plusExtends";

let signIn: Function = async (
  data: any,
  vm: Login,
  parentvm: Login
): Promise<void> => {
  parentvm.loading = true;

  await axios
    .post("/login", data)
    .then((res) => {
      let info: responseData = res.data;
      console.log(info);

      //一切正常
      if (info.code === "0000") {
        //保存token
        vm.$store.commit("setToken", info.data.token);
        parentvm.loading = false;
        vm.$router.push({ name: "home" });
      } else {
        //报错
        parentvm.loading = false;
        parentvm.$refs.alert.showUp(2000, "error", info.message);
      }
    })
    .catch((err) => {
      console.log(err);
      parentvm.loading = false;
      parentvm.$refs.alert.showUp(3000, "error", "未知错误!");
    });

  //本地存储
  plusExtends(() => {
    plus.storage.setItem("user", data.username);
    if (vm.savePwd) {
      plus.storage.setItem("password", data.password);
    } else {
      plus.storage.removeItem("password");
    }
  });

  // //tmp
  // setTimeout(() => {
  //   vm.$store.commit("setToken", "123456");
  //   parentvm.loading = false;
  //   vm.$router.push({ name: "home" });
  // }, 2000);
};

export default signIn;
