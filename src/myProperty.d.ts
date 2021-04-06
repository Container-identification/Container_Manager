import Vue from "vue";
import * as echarts from "echarts";
import { containerInfo, infoData } from "./store/module";

declare module "vue/types/vue" {
  interface Vue {
    $api: api;
    //都会有的token
    token: string;
    //echarts组件
    $echarts: echarts;
  }
  //自定义的alert组件
  interface MyAlert extends Vue {
    showUp: alertFunction;
  }
  //自定义的tabbar组件
  interface MyTabbar extends Vue {
    redirect: tabbarRedirect;
  }
  //搜索组件
  interface Search extends Vue {
    upMoving: boolean;
    infoFlag: boolean;
  }
  //登录组件
  interface Login extends Vue {
    savePwd: boolean;
    loading: Boolean;
    loadingText: String;
    $refs: {
      alert: MyAlert;
    };
  }
  //主页
  interface Home extends Vue {
    loading: Boolean;
    loadingText: String;
    loadingImg: String;
    $refs: {
      alert: MyAlert;
      tabbar: MyTabbar;
    };
  }
  //单个堆场页面
  interface SingleYard extends Vue {
    closeOperate: Function;
  }
  //操作页面
  interface Operate extends Vue {
    editInfo: containerInfo;
    identifyInfo: infoData;
  }
  //统计页面
  interface Statistics extends Vue {
    yardData: any;
  }
}

//自定义的alert组件的调用函数的参数类型
interface alertFunction {
  (
    duration: number,
    type: "success" | "info" | "error" | "warning",
    text?: string
  ): void;
}

//自定义的tabbar组件的跳转函数
interface tabbarRedirect {
  (name: "shot" | "manage" | "search" | "user", payload?: any): void;
}

//upload返回的信息
interface uploadRes {
  container: string;
  containerImg: string;
}

//api接口类型
interface api {
  upload: uploadRequest;
  signIn: signInRequest;
  search: searchRequest;
  queryYard: queryYardRequest;
  identify: identifyRequest;
  operate: operateRequest;
  deleteContainer: deleteContainerRequest;
  yardStatistics: yardStatisticsRequest;
}

//api请求函数类型
interface uploadRequest {
  (filePath: string, vm: Vue, token: string, toSearch: boolean): uploadRes;
}

interface signInRequest {
  (data: any, vm: Vue, parentvm: Vue): void;
}

interface searchRequest {
  (data: any, vm: Vue, homevm: Vue, toInfo: boolean): void;
}

interface queryYardRequest {
  (data: any, homevm: Vue): void;
}

interface identifyRequest {
  (file: string, token: string, vm: Vue, homevm: Vue): void;
}

interface operateRequest {
  (data: any, vm: Vue, homevm: Vue): void;
}

interface deleteContainerRequest {
  (data: any, vm: Vue, homevm: Vue): void;
}

interface yardStatisticsRequest {
  (vm: Vue, homevm: Vue, token: string): void;
}

declare module "animate.css" {}
