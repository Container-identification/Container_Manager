import axios from "axios";
import { Home, Operate } from "vue/types/vue";

let operate: Function = async (
  data: any,
  vm: Operate,
  homevm: Home
): Promise<void> => {
  homevm.loading = true;
  homevm.loadingText = "入库中";
  await axios
    .post("/operate", data)
    .then((res) => {
      let info: responseData = res.data;
      console.log(JSON.stringify(info));
      if (info.code === "0000") {
        //一切正常
        //修改本地数据
        let payload = {
          row: data.locate.row,
          col: data.locate.col,
          pageData: data.locate.pageData,
          info: {
            container: data.container,
            owner: data.owner,
            payLoad: data.payLoad,
            sta: data.sta,
            status: data.status,
            tare: data.tare,
            type: data.type,
          },
        };
        vm.$store.commit("yard/operateYard", payload);
        homevm.$refs.alert.showUp(2000, "success", "入库成功！");
      } else {
        //已知错误
        homevm.$refs.alert.showUp(2000, "warning", info.message);
      }
    })
    .catch((err) => {
      console.log(err);
      homevm.$refs.alert.showUp(2000, "warning", "未知错误");
    });

  homevm.loading = false;

  // //tmp
  // console.log(data);
  // let payload = {
  //   row: data.order.row,
  //   col: data.order.col,
  //   pageData: data.order.pageData,
  //   info: {
  //     container: data.container,
  //     owner: data.owner,
  //     payload: data.payload,
  //     sta: data.sta,
  //     status: data.status,
  //     tare: data.tare,
  //     type: data.type,
  //   },
  // };
  // vm.$store.commit("yard/operateYard", payload);
  // setTimeout(() => {
  //   homevm.loading = false;
  // }, 2000);
};

export default operate;
