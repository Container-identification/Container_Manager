import axios from "axios";
import { Search, Home } from "vue/types/vue";

let search: Function = async (
  data: any,
  vm: Search,
  homevm: Home,
  toInfo: boolean
): Promise<void> => {
  homevm.loading = true;
  homevm.loadingText = "查询中";

  await axios
    .post("/logistics", data)
    .then((res) => {
      homevm.loading = false;
      let info: responseData = res.data;
      console.log(info);
      if (info.code === "0000") {
        //一切正常
        vm.$store.commit("info/setResult", info.data);
        if (toInfo) {
          vm.$router.push({ name: "info" });
        }
      } else {
        homevm.$refs.alert.showUp(2000, "error", info.message);
      }
    })
    .catch((err) => {
      homevm.loading = false;
      homevm.$refs.alert.showUp(2000, "error", "未知错误");
    });

  // //tmp
  // return new Promise((resolve, reject) => {
  //   setTimeout(() => {
  //     resolve(null);
  //   }, 1000);
  // }).then(() => {
  //   homevm.loading = false;
  //   let tmp = {
  //     container: "HLBU 9468471",
  //     owner: "HAPAG-LLOYD 赫伯罗特",
  //     status: 0,
  //     sta: "2",
  //     type: "22RT",
  //     tare: "3000",
  //     payload: "29000",
  //     table: [
  //       {
  //         operate: "入堆",
  //         place: "1号堆场",
  //         date: "2021-01-14",
  //         time: "07:51",
  //         person: "运输员01",
  //       },
  //       {
  //         operate: "转移",
  //         place: "2号堆场",
  //         date: "2021-01-14",
  //         time: "11:58",
  //         person: "运输员01",
  //       },
  //       {
  //         operate: "出堆",
  //         place: "2号堆场",
  //         date: "2021-01-20",
  //         time: "08:23",
  //         person: "运输员05",
  //       },
  //     ],
  //   };

  //   vm.$store.commit("info/setResult", tmp);
  //   if (toInfo) {
  //     vm.$router.push({ name: "info" });
  //   }
  // });
};

export default search;
