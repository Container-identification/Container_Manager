import Vue from "vue";

declare module "vue/types/vue" {
  interface Vue {
    $api: any;
    loading: Boolean;
    loadingText: String;
  }
  interface Home extends Vue {
    uploadFlag: Boolean;
    uploadNotify: String;
  }
}
