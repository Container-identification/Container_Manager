import Vue from "vue";
import VueRouter, { RouteConfig } from "vue-router";

Vue.use(VueRouter);

const routes: Array<RouteConfig> = [
  {
    path: "/home",
    name: "home",
    component: () => import("@/views/home.vue"),
    children: [
      {
        path: "index",
        name: "shot",
        component: () => import("@/components/home/shot.vue"),
      },
      {
        path: "search",
        name: "search",
        component: () => import("@/components/home/search.vue"),
      },
      {
        path: "user",
        name: "user",
        component: () => import("@/components/home/user.vue"),
        children: [
          {
            path: "message",
            name: "message",
            component: () => import("@/components/home/user/message.vue"),
          },
          {
            path: "index",
            name: "userIndex",
            component: () => import("@/components/home/user/index.vue"),
          },
          {
            path: "hrmanage",
            name: "hrManage",
            component: () => import("@/components/home/user/hrManage.vue"),
          },
        ],
      },
      {
        path: "manage",
        name: "manage",
        component: () => import("@/components/home/manage.vue"),
        children: [
          {
            path: "statistics",
            name: "statistics",
            component: () => import("@/components/home/manage/statistics.vue"),
          },
        ],
      },
    ],
  },
  {
    path: "/info",
    name: "info",
    component: () => import("@/views/Info.vue"),
  },
  {
    path: "/login",
    name: "login",
    component: () => import("@/views/login.vue"),
  },
];

const router = new VueRouter({
  routes,
});

export default router;
