import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
//vuetify
import vuetify from "./plugins/vuetify";

//animate.css
import animated from "animate.css";
Vue.use(animated);

//api
import { api } from "@/api/index";
Vue.prototype.$api = api;

//html5+
// import plusExtends from '@/plugins/plusExtends'
// Vue.prorotype.$plusExtends=plusExtends

Vue.config.productionTip = false;

new Vue({
  router,
  store,
  vuetify,
  render: (h) => h(App),
}).$mount("#app");
