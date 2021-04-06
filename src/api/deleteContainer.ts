import axios from "axios";
import { Home, Operate } from "vue/types/vue";

let deleteContainer: Function = async (
  data: any,
  vm: Operate,
  homevm: Home
): Promise<void> => {
  console.log(data);
  homevm.loading = true;
  homevm.loadingText = "出库中";

  await axios
    .post("/delete", data)
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
        };
        vm.$store.commit("yard/deleteYard", payload);
        homevm.$refs.alert.showUp(2000, "success", "出库成功！");
      } else {
        homevm.$refs.alert.showUp(2000, "warning", info.message);
      }
    })
    .catch((err) => {
      console.log(err);
      homevm.$refs.alert.showUp(2000, "warning", "未知错误");
    });

  homevm.loading = false;

  // //tmp
  // setTimeout(() => {
  //   let payload = {
  //     row: data.order.row,
  //     col: data.order.col,
  //     pageData: data.order.pageData,
  //   };
  //   vm.$store.commit("yard/deleteYard", payload);
  //   homevm.loading = false;
  // }, 2000);
};

export default deleteContainer;
