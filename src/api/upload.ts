const config = require("../config.json");
import { Home } from "vue/types/vue";

let upload: Function = (file: String, vm: Home): void => {
  vm.loading = true;
  vm.loadingText = "创建上传对象中";
  // console.log('start upload '+file)
  //创建上传对象
  let upload = plus.uploader.createUpload(
    config.uploadAddress + "/upload",
    {
      timeout: 30,
      retry: 0,
    },
    (upload: any, status: Number) => {
      vm.loadingText = "";
      vm.loading = false;
      if (status === 200) {
        let res = JSON.parse(upload.responseText);
        //切换路由
        vm.$router.push("info");
        //更新数据
        vm.$store.commit("info/setMsg", res.infos);
      } else {
        vm.uploadFlag = true;
        vm.uploadNotify = "上传失败";
        if (upload.responseText != undefined) {
          let res = JSON.parse(upload.responseText);
          console.log(res.msg);
        }
      }
    }
  );
  // console.log('success createUpload!')
  vm.loadingText = "添加文件中";
  //添加文件
  if (
    upload.addFile(file, {
      key: "file",
    })
  ) {
    // console.log('success add file!')
    //监听进度变化
    upload.addEventListener("statechanged", (upload: any, status: Number) => {
      vm.loadingText = `已上传${(((upload.uploadedSize / upload.totalSize) *
        100) as Number).toFixed(2)}%`;
    });
    vm.loadingText = "准备上传文件";
    //开始发送
    upload.start();
  } else {
    vm.uploadFlag = true;
    vm.uploadNotify = "添加失败";
  }
};

export default upload;
