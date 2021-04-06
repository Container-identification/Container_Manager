import { uploadRes } from "@/myProperty";
import { Home } from "vue/types/vue";
const config = require("@/config.json");

let upload: Function = async (
  file: string,
  homevm: Home,
  token: string,
  toSearch: boolean
): Promise<uploadRes> => {
  return new Promise((resolve, reject) => {
    homevm.loading = true;
    homevm.loadingText = "创建上传对象中";
    // console.log('start upload '+file)

    //创建上传对象
    let upload = plus.uploader.createUpload(
      config.serverAddress + "/upload",
      {
        timeout: 120,
        retry: 0,
      },
      (upload, status) => {
        //关闭遮罩层
        homevm.loadingText = "";
        homevm.loading = false;
        if (status === 200) {
          //获取返回数据 JSON格式
          console.log(upload.responseText);
          let info: responseData = JSON.parse(upload.responseText);
          //一切正常
          if (info.code === "0000") {
            //跳转到搜索页面
            if (toSearch) {
              homevm.$refs.tabbar.redirect("search", {
                container: info.data.container,
                containerImg: config.serverAddress + info.data.URL,
              });
            }
            //正常返回
            homevm.$refs.alert.showUp(2000, "success", "识别成功！");
            resolve({
              container: info.data.container,
              containerImg: config.serverAddress + info.data.URL,
            });
          } //其他已知错误
          else {
            homevm.$refs.alert.showUp(2000, "error", info.message);
          }
        } else {
          homevm.$refs.alert.showUp(2000, "error", "上传失败");
          // console.log(info);
        }
      }
    );

    homevm.loadingText = "添加文件中";
    //添加文件
    if (
      upload.addFile(file, {
        key: "file",
      })
    ) {
      // console.log('success add file!')
      //监听进度变化
      upload.addEventListener("statechanged", (upload, status) => {
        // homevm.loadingText = `已上传${(((upload.uploadedSize /
        //   upload.totalSize) *
        //   100) as Number).toFixed(2)}%`;
        homevm.loadingText = "上传中";
      });
      homevm.loadingText = "准备上传文件";
      //token注入
      upload.addData("token", token);
      //开始发送
      upload.start();
    } else {
      homevm.$refs.alert.showUp(2000, "error", "添加文件失败");
    }
  });

  // return new Promise((resolve, reject) => {
  //   homevm.loadingText = "识别中";
  //   homevm.loadingImg =
  //     "https://gimg2.baidu.com/image_search/src=http%3A%2F%2F2c.zol-img.com.cn%2Fproduct%2F124_500x2000%2F748%2FceZOdKgDAFsq2.jpg&refer=http%3A%2F%2F2c.zol-img.com.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1618814707&t=95428b73892995749459598a42fc607d"; //添加loadingImg
  //   setTimeout(() => {
  //     resolve(null);
  //   }, 2000);
  // }).then(() => {
  //   homevm.loading = false;

  //   //tmp
  //   let container = "TCLU 8075745"; //识别结果
  //   let containerImg =
  //     "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3593062272,3922766956&fm=26&gp=0.jpg";

  //   homevm.loadingImg = ""; //删除loadingimg
  //   if (toSearch) {
  //     homevm.$refs.tabbar.redirect("search", {
  //       container,
  //       containerImg,
  //     });
  //   }
  //   homevm.$refs.alert.showUp(2000, "success", "识别成功！");
  //   return container;
  // });
};

export default upload;
