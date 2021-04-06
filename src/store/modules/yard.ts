import Vue from "vue";
import { Module } from "vuex";
import { myRootState, yardModule, yardArray } from "../module";

const yard: Module<yardModule, myRootState> = {
  namespaced: true,
  state() {
    return {
      data: [
        [
          [1, 1, 1, 0, 1],
          [1, 1, 1, 1, 1],
          [0, 0, 1, 1, 1],
          [2, 2, 2, 1, 1],
          [1, 1, 0, 0, 0],
          [1, 1, 2, 1, 1],
          [3, 3, 1, 1, 0],
          [1, 0, 3, 2, 1],
        ],
        [
          [1, 1, 3, 1, 1],
          [1, 1, 1, 1, 1],
          [3, 3, 3, 2, 1],
          [1, 1, 2, 1, 1],
          [1, 3, 1, 1, 1],
          [2, 1, 1, 1, 3],
          [2, 2, 1, 1, 3],
        ],
        [
          [1, 1, 1, 1, 1],
          [2, 2, 2, 2, 2],
          [1, 1, 1, 1, 1],
          [1, 1, 1, 1, 1],
          [3, 3, 3, 2, 2],
          [1, 1, 1, 1, 1],
          [3, 3, 2, 2, 2],
          [1, 1, 1, 1, 1],
        ],
        [
          [1, 1, 1, 1, 1],
          [2, 2, 2, 2, 2],
          [1, 1, 1, 1, 1],
          [1, 1, 1, 1, 1],
          [3, 3, 3, 2, 2],
          [1, 1, 1, 1, 1],
          [3, 3, 2, 2, 2],
          [1, 1, 1, 1, 1],
        ],
      ],
      yardIndex: 0,
      isAllYard: true,
      yardArray: [],
      sta: [],
    };
  },
  mutations: {
    //tmp
    setYard(state, data) {
      Vue.set(state.data[data.index][data.row], data.col, data.val);
    },

    operateYard(state, data) {
      Vue.set(state.yardArray[data.pageData][data.row], data.col, data.info);
    },
    deleteYard(state, data) {
      Vue.set(state.yardArray[data.pageData][data.row], data.col, undefined);
    },
    setIndex(state, index: number) {
      state.yardIndex = index;
    },
    changeView(state) {
      state.isAllYard = !state.isAllYard;
    },
    initYard(state, { yardArray, sta }) {
      state.yardArray = yardArray;
      state.sta = sta;
    },
  },
};

export default yard;
