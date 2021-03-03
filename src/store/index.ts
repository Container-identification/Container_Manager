import Vue from "vue";
import Vuex from "vuex";

import info from "./modules/info";
import yard from "./modules/yard";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    token: "",
    tmpIndex: 0,
  },
  mutations: {
    setToken(state, value) {
      state.token = value;
    },
    setTmpIndex(state) {
      if (state.tmpIndex + 1 >= 4) {
        state.tmpIndex = 0;
      } else {
        state.tmpIndex++;
      }
    },
  },
  actions: {
    asyncTmpIndex(context) {
      setTimeout(() => {
        context.commit("setTmpIndex");
      }, 500);
    },
  },
  modules: {
    info,
    yard,
  },
});
