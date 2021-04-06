import axios from "axios";
import { Home } from "vue/types/vue";
import yardHandle from "@/utils/yardHandle";

let queryYard: Function = async (data: any, homevm: Home): Promise<void> => {
  homevm.loading = true;
  homevm.loadingText = "查询堆场信息中";

  await axios
    .post("/queryYard", data)
    .then((res) => {
      let info: responseData = res.data;
      console.log(info);
      if (info.code === "0000") {
        //一切正常
        //处理数据
        let yardArray = yardHandle(info.data.info);
        //存储数据
        homevm.$store.commit("yard/initYard", {
          yardArray,
          sta: info.data.sta,
        });
        homevm.loading = false;
      } else {
        homevm.$refs.alert.showUp(2000, "warning", info.message);
        homevm.loading = false;
      }
    })
    .catch((err) => {
      homevm.loading = false;
      homevm.$refs.alert.showUp(2000, "warning", "未知错误");
    });

  // //tmp
  // let test = {
  //   sta: [
  //     {
  //       id: "1",
  //       content: "haha",
  //     },
  //     {
  //       id: "2",
  //       content: "222",
  //     },
  //   ],
  //   info: [
  //     {
  //       container: "HLBU 9468471",
  //       owner: "HAPAG-LLOYD 赫伯罗特",
  //       status: 0,
  //       type: "22RT",
  //       tare: "3000",
  //       payload: "29000",
  //       order: "20",
  //       sta: "2",
  //       url: "ssssssssss",
  //     },
  //     {
  //       container: "SUDU 1155480",
  //       owner: "MAERSK 马士基",
  //       status: 1,
  //       type: "22RT",
  //       tare: "3200",
  //       order: "22",
  //       sta: "0",
  //       url: "aaaaaaaaaaaaa",
  //     },
  //     {
  //       container: "FCIU 4521291",
  //       owner: "MSC 地中海",
  //       status: 1,
  //       type: "22RT",
  //       tare: "2180",
  //       order: "41",
  //       sta: "0",
  //       url: "aaaaaaaaaaaaa",
  //     },
  //     {
  //       container: "TCLU 8075745",
  //       owner: "MAERSK 马士基",
  //       status: 1,
  //       type: "22RT",
  //       tare: "3890",
  //       order: "121",
  //       sta: "0",
  //       url: "aaaaaaaaaaaaa",
  //     },
  //   ],
  // };
  // setTimeout(() => {
  //   let yardArray = yardHandle(test.info);
  //   homevm.$store.commit("yard/initYard", { yardArray, sta: test.sta });
  //   homevm.loading = false;
  // }, 2000);
};

export default queryYard;
