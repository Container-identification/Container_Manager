const info = {
  namespaced: true,
  state() {
    return {
      infos: {
        msg1: "",
        msg2: "",
        msg3: "",
      },
    };
  },
  mutations: {
    setMsg(state: any, infos: object) {
      state.infos = infos;
    },
  },
};

export default info;
