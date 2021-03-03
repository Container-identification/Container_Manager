<template>
  <div id="operate">
    <v-card elevation="2">
      <v-card-title>集装箱信息</v-card-title>
      <v-card-text>
        <div class="show" v-if="status">
          <div class="num">集装箱号 : {{ testData[tmpIndex].num }}</div>
          <div class="owner">箱主 : {{ testData[tmpIndex].owner }}</div>
          <div class="status">状态 : {{ testData[tmpIndex].status }}</div>
          <div class="location">位置 : {{ locationText }}</div>
          <div class="type">箱型 : {{ testData[tmpIndex].type }}</div>
          <div class="weight">重量 : {{ testData[tmpIndex].weight }}</div>
          <div class="bear">承重 : {{ testData[tmpIndex].bear }}</div>
          <v-img
            height="200px"
            :src="require(`../../../assets/containerTest${tmpIndex + 1}.jpg`)"
          ></v-img>
        </div>
        <div class="edit" v-else>
          <v-text-field
            dense
            label="输入集装箱号"
            append-icon="fa-camera"
            @click:append="identify"
          ></v-text-field>
          <v-text-field dense label="输入箱主"></v-text-field>
          <v-text-field
            disabled
            dense
            label="位置"
            :value="locationText"
          ></v-text-field>
          <v-text-field dense label="输入箱型"></v-text-field>
          <v-text-field dense label="输入重量"></v-text-field>
          <v-text-field dense label="输入承重"></v-text-field>
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
import { MyAlert } from "vue/types/vue";

@Component({
  name: "operate",
})
export default class Operate extends Vue {
  //data
  testData = [
    {
      num: "HLBU 94669410",
      owner: "HAPAG-LLOYD 罗特",
      status: "堆场中",
      // location: "101-01",
      type: "22RT",
      weight: "3000kg",
      bear: "29000kg",
    },
    {
      num: "SUDU 1155480",
      owner: "MAERSK 马士基",
      status: "堆场中",
      type: "22RT",
      weight: "3200",
      bear: "27280",
    },
    {
      num: "FCIU 4521291",
      owner: "MSC 地中海",
      status: "堆场中",
      type: "22RT",
      weight: "2180",
      bear: "28300",
    },
  ];
  //tmp
  tmpIndex = Math.floor(Math.random() * 3);
  private status = this.$route.params.id != "1";
  private location = {
    index: parseInt(this.$route.params.index),
    row: this.$route.params.row,
    col: this.$route.params.col,
  };

  //methods
  back() {
    this.$router.back();
  }
  goOut(flag: boolean) {
    //tmp
    if (flag) {
      this.$store.commit(
        "yard/setYard",
        Object.assign({ val: 1 }, this.location)
      );
    } else {
      this.$store.commit(
        "yard/setYard",
        Object.assign({ val: 2 }, this.location)
      );
    }
    this.back();
  }
  identify() {
    //tmp
    plus.gallery.pick(
      (file) => {
        this.$parent.$parent.loading = true;
        this.$parent.$parent.loadingText = "上传中";
        setTimeout(() => {
          this.$parent.$parent.loading = false;
          (this.$parent.$parent.$refs.alert as MyAlert).showUp(
            2000,
            "success",
            "识别成功!"
          );
          this.status = true;
        }, 2000);
      },
      (error) => {
        console.log("error!");
      },
      {
        multiple: false,
        permissionAlert: true,
      }
    );
  }
  insertImg() {
    console.log("插入图片");
  }

  //computed
  get locationText() {
    return `${this.location.row}-${this.location.col}`;
  }
}
</script>

<style scoped lang="scss">
#operate {
  position: fixed;
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
