import axios from "axios";
import { Home, Statistics } from "vue/types/vue";
import { ESOption } from "@/plugins/echarts";

let yardStatistics: Function = async (
  vm: Statistics,
  homevm: Home,
  token: string
): Promise<void> => {
  homevm.loading = true;
  homevm.loadingText = "获取统计信息中";

  await axios
    .post("/yardStatistics", { token })
    .then((res) => {
      let info: responseData = res.data;
      console.log(info);
      if (info.code === "0000") {
        //一切正常
        vm.yardData = info.data.info[0];
        //生成统计图
        //获取container
        let myChart = vm.$echarts.init(vm.$refs.charts);
        //配置数据
        let option: ESOption = {
          series: [
            {
              name: "堆场库存使用状况统计图",
              type: "pie",
              label: {
                position: "inside",
              },
              data: [
                {
                  name: "剩余库存",
                  value: vm.yardData.totalWeight - vm.yardData.usedWeight,
                },
                {
                  name: "已用库存",
                  value: vm.yardData.usedWeight,
                },
              ],
            },
          ],
        };
        //生成统计图
        myChart.setOption(option);
      } else {
        homevm.$refs.alert.showUp(2000, "warning", info.message);
      }
    })
    .catch((err) => {
      console.log(err);
      homevm.$refs.alert.showUp(2000, "warning", "未知错误");
    });

  homevm.loading = false;

  // await new Promise((resolve, reject) => {
  //   setTimeout(() => {
  //     resolve(null);
  //   }, 2000);
  // }).then(() => {
  //   let testData = {
  //     total: "104",
  //     sort: [
  //       {
  //         name: "20英寸开顶箱",
  //         count: 46,
  //       },
  //       {
  //         name: "20英寸冷冻箱",
  //         count: 25,
  //       },
  //       {
  //         name: "40英寸干货箱",
  //         count: 21,
  //       },
  //       {
  //         name: "40英寸冷冻箱",
  //         count: 9,
  //       },
  //       {
  //         name: "45英寸冷高箱",
  //         count: 3,
  //       },
  //     ],
  //     other: [
  //       {
  //         name: "需要维护的货箱个数",
  //         count: "2",
  //       },
  //     ],
  //     totalWeight: "1004",
  //     usedWeight: "434",
  //   };
  //   vm.yardData = testData;
  //   //生成统计图
  //   //获取container
  //   let myChart = vm.$echarts.init(vm.$refs.charts);
  //   //配置数据
  //   let option: ESOption = {
  //     series: [
  //       {
  //         name: "堆场库存使用状况统计图",
  //         type: "pie",
  //         label: {
  //           position: "inside",
  //         },
  //         data: [
  //           {
  //             name: "总库存",
  //             value: vm.yardData.totalWeight,
  //           },
  //           {
  //             name: "已用库存",
  //             value: vm.yardData.usedWeight,
  //           },
  //         ],
  //       },
  //     ],
  //   };
  //   //生成统计图
  //   myChart.setOption(option);

  //   homevm.loading = false;
  // });
};

export default yardStatistics;
