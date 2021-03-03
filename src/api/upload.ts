const config = require("../config.json");
import { MyAlert } from "vue/types/vue";
import Home from "@/views/home.vue";

let upload: Function = (file: string, vm: Home, token: string): void => {
  vm.loading = true;
  vm.loadingText = "创建上传对象中";
  // console.log('start upload '+file)

  // //创建上传对象
  // let upload = plus.uploader.createUpload(
  //   config.serverAddress + "/upload",
  //   {
  //     timeout: 30,
  //     retry: 0,
  //   },
  //   (upload, status) => {
  //     //关闭遮罩层
  //     vm.loadingText = "";
  //     vm.loading = false;
  //     //获取返回数据 JSON格式
  //     let info: responseData = upload.responseText;
  //     console.log(info);
  //     if (status === 200) {
  //       //一切正常
  //       if (info.code === "0000") {
  //         // //修改数据
  //         // //待补充
  //         // vm.$store.commit("info/setMsg", info.data);
  //         // //跳转
  //         // vm.$router.push({ name: "info" });
  //       } //其他已知错误
  //       else {
  //         // (vm.$refs.alert as MyAlert).showUp(2000, "error", info.message);
  //         //tmp
  //         vm.$router.push({ name: "info" });
  //       }
  //     } else {
  //       (vm.$refs.alert as MyAlert).showUp(2000, "error", "上传失败");
  //       console.log(info);
  //     }
  //   }
  // );

  // vm.loadingText = "添加文件中";
  // //添加文件
  // if (
  //   upload.addFile(file, {
  //     key: "file",
  //   })
  // ) {
  //   // console.log('success add file!')
  //   //监听进度变化
  //   upload.addEventListener("statechanged", (upload, status) => {
  //     vm.loadingText = `已上传${(((upload.uploadedSize / upload.totalSize) *
  //       100) as Number).toFixed(2)}%`;
  //   });
  //   vm.loadingText = "准备上传文件";
  //   //token注入
  //   upload.addData("token", token);
  //   //开始发送
  //   upload.start();
  // } else {
  //   (vm.$refs.alert as MyAlert).showUp(2000, "error", "添加文件失败");
  // }

  //tmp
  setTimeout(() => {
    vm.loadingText = "上传中";
    setTimeout(() => {
      vm.loading = false;
      vm.loadingText = "";
      vm.$router.push({ name: "info" });
    }, 1000);
  }, 1000);
};

export default upload;
