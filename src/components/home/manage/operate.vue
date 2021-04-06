<template>
  <div id="operate">
    <v-card elevation="2">
      <v-card-title>集装箱信息</v-card-title>
      <v-card-text>
        <div class="show" v-if="status">
          <div class="num">集装箱号 : {{ containerInfo.container }}</div>
          <div class="owner">箱主 : {{ containerInfo.owner }}</div>
          <div class="status">状态 : {{ containerStatus }}</div>
          <div class="location">位置 : {{ locationText }}</div>
          <div class="type">箱型 : {{ containerInfo.type }}</div>
          <div class="weight">重量 : {{ containerInfo.tare }}</div>
          <div class="bear">承重 : {{ containerInfo.payLoad }}</div>
          <v-img height="200px" :src="containerImg"></v-img>
        </div>
        <div class="edit" v-else>
          <v-text-field
            dense
            label="输入集装箱号"
            append-icon="fa-camera"
            @click:append="identify"
            v-model="editInfo.container"
          ></v-text-field>
          <v-text-field
            dense
            label="输入箱主"
            v-model="editInfo.owner"
          ></v-text-field>
          <v-text-field
            disabled
            dense
            label="位置"
            :value="locationText"
          ></v-text-field>
          <v-text-field
            dense
            label="输入箱型"
            v-model="editInfo.type"
          ></v-text-field>
          <v-text-field
            dense
            label="输入重量"
            v-model="editInfo.tare"
          ></v-text-field>
          <v-text-field
            dense
            label="输入承重"
            v-model="editInfo.payLoad"
          ></v-text-field>
          <v-btn fab elevation="1" @click="insertImg">
            <v-icon>fa-camera-retro</v-icon>
          </v-btn>
          <div class="text">给集装箱拍照</div>
        </div>
      </v-card-text>
      <v-card-actions>
        <v-btn
          :color="status ? 'purple' : 'blue'"
          @click="goOut(status ? true : false)"
          >{{ status ? "出库" : "入库" }}</v-btn
        >
        <v-btn color="blue" @click="back">返回</v-btn>
      </v-card-actions>
    </v-card>
  </div>
</template>

<script lang="ts">
import { Component } from "vue-property-decorator";
import Vue from "vue";
import { Home, SingleYard } from "vue/types/vue";
import { containerInfo, infoData } from "@/store/module";
import { mapState } from "vuex";
import yardHandleOut from "@/utils/yardHandleOut";
const config = require("@/config.json");

@Component({
  name: "operate",
  props: {
    containerInfo: {
      required: true,
    },
    locate: {
      required: true,
    },
  },
  computed: {
    ...mapState({
      token: (state: any) => state.token,
      identifyInfo: (state: any) => state.info.result,
    }),
  },
})
export default class Operate extends Vue {
  //data
  public containerInfo: containerInfo | undefined;
  public locate: any;
  public editInfo: containerInfo = {
    container: "",
    status: 0,
    order: "",
    owner: "",
    type: "",
    tare: "",
    payLoad: "",
    sta: 0,
    url: "", //???
  };
  public identifyInfo!: infoData;

  //methods
  back() {
    (this.$parent as SingleYard).closeOperate();
  }
  goOut(flag: boolean) {
    if (flag) {
      //出库
      let data: any = {
        token: this.token,
        order: yardHandleOut(this.locate),
        locate: this.locate,
      };
      this.$api.deleteContainer(data, this, this.$parent.$parent.$parent);
    } else {
      //入库
      let data: any = this.editInfo;
      data.token = this.token;
      data.order = yardHandleOut(this.locate);
      data.locate = this.locate;
      this.$api.operate(data, this, this.$parent.$parent.$parent);
    }
    this.back();
  }
  async identify() {
    plus.gallery.pick(
      (file) => {
        this.$api.identify(
          file,
          this.token,
          this,
          this.$parent.$parent.$parent
        );
      },
      (err) => {
        console.log(err.message);
        (this.$parent.$parent.$parent as Home).$refs.alert.showUp(
          2000,
          "warning",
          "添加失败"
        );
      },
      { multiple: false, permissionAlert: true }
    );

    // //tmp
    // await this.$api.identify(
    //   "ssssss",
    //   this.token,
    //   this,
    //   this.$parent.$parent.$parent
    // );
  }
  insertImg() {
    console.log("插入图片");
  }

  //computed
  get locationText() {
    //计算实际位置
    let row = Math.floor(this.locate.pageData / 3) * 8 + this.locate.row;
    let col = Math.floor(this.locate.pageData % 3) * 5 + this.locate.col;
    return `${row + 1}-${col + 1}`;
  }
  get status() {
    // return this.containerInfo !== undefined;
    return this.containerInfo != undefined;
  }
  get containerStatus() {
    return this.containerInfo?.status == 0 ? "运输中" : "堆场内";
  }
  get containerImg() {
    return config.serverAddress + this.containerInfo?.url;
  }
}
</script>

<style scoped lang="scss">
#operate {
  position: fixed;
  top: 0;
  z-index: 1;
  height: 100vh;
  width: 100vw;
  background-color: rgba(#9e9e9e, 0.9);
  .v-card {
    margin: 70px auto 0 auto;
    width: 80vw;
    padding: 10px;
    .v-card__text {
      font-size: 0.95rem;
      .show {
        .v-image {
          margin-top: 20px;
        }
      }
      .edit {
        .v-btn {
          margin: 0 auto;
          display: block;
        }
      }
      .text {
        margin-top: 10px;
        font-size: 1.1rem;
        text-align: center;
      }
    }
    .v-card__actions {
      .v-btn {
        margin: 0 auto;
        color: white;
      }
    }
  }
}
</style>
