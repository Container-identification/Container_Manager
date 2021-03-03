import Vue from "vue";

declare module "vue/types/vue" {
  interface Vue {
    $api: api;
    //每个组件可能存在的自定义loading组件
    loading: Boolean;
    loadingText: String;
    //都会有的token
    token: string;
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
  //历史组件
  interface History extends Vue {
    hasRes: boolean;
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
  (name: string): void;
}

//api接口类型
interface api {
  upload: uploadRequest;
  signIn: httpRequest;
  search: httpRequest;
  history: httpRequest;
}

//api请求函数类型
interface httpRequest {
  (data: object, vm: Vue, additonalVM?: Vue): void;
}

interface uploadRequest {
  (filePath: string, vm: Vue, token: string): void;
}

declare module "animate.css" {}
