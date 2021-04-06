import { Home, Operate } from "vue/types/vue";

let identify: Function = async (
  file: string,
  token: string,
  vm: Operate,
  homevm: Home
): Promise<void> => {
  //先查询箱号
  let { container } = await vm.$api.upload(file, homevm, token, false);
  //后获取信息
  let tmp = {
    token,
    container,
  };
  await vm.$api.search(tmp, vm, homevm, false);
  //修改共同数据
  vm.editInfo.container = vm.identifyInfo.container;
  vm.editInfo.owner = vm.identifyInfo.owner;
  vm.editInfo.type = vm.identifyInfo.type;
  vm.editInfo.tare = vm.identifyInfo.tare;
  vm.editInfo.payLoad = vm.identifyInfo.payLoad;
};

export default identify;
