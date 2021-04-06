import plusExtends from "@/plugins/plusExtends";
import { Module } from "vuex/types/index";
import { myRootState, histroyModule } from "../module";

const yard: Module<histroyModule, myRootState> = {
  namespaced: true,
  state() {
    return {
      count: 0,
      list: [],
    };
  },
  mutations: {
    //从app本地记录中取出数据
    init(state) {
      //获取历史总条数
      let count = parseInt(plus.storage.getItem("historyCount"));
      state.count = count ? count : 0;
      //获取所有历史信息
      for (let i = 0; i < count; i++) {
        state.list.push(plus.storage.getItem(`historyItem${i}`));
      }
    },
    addHistory(state, val: string) {
      //修改vuex的数据
      state.list[state.count] = val;
      //修改本地存储的历史信息
      plus.storage.setItem(`historyItem${state.count}`, val);
      //增加vuex的数目
      state.count++;
      //设置本地数据的数目
      plus.storage.setItem("historyCount", state.count.toString());
    },
    clearHistory(state) {
      //清除app本地数据
      for (let i = 0, count = state.count; i < count; i++) {
        plus.storage.removeItem(`historyItem${i}`);
      }
      plus.storage.removeItem("historyCount");
      //清除vuex的数据
      state.count = 0;
      state.list = [];
    },
  },
};

export default yard;
