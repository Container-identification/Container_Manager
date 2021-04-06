import { Module } from "vuex";
import { infoModule, myRootState, infoData } from "../module";

const info: Module<infoModule, myRootState> = {
  namespaced: true,
  state() {
    return {
      result: undefined,
    };
  },
  mutations: {
    setResult(state, info: infoData) {
      state.result = info;
    },
  },
};

export default info;
