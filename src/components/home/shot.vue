<template>
  <div id="shot">
    <header>
      <div class="text">箱号识别</div>
      <v-btn outlined @click="getImg(false)"
        ><v-icon>fa-picture-o</v-icon>
        <!-- <span>相册中选择</span> -->
      </v-btn>
    </header>
    <div class="warp">
      <v-btn
        id="shotImg"
        color="primary"
        elevation="5"
        outlined
        rounded
        @click="getImg(true)"
      >
        <v-icon size="120px">fa-camera</v-icon>
      </v-btn>
      <div class="wave"></div>
      <v-btn
        elevation="1"
        @click="toSearch"
        class="searchLink"
        color="light-blue lighten-2"
        >手动输入货箱号查询</v-btn
      >
    </div>
  </div>
</template>

<script lang="ts">
import { Component } from "vue-property-decorator";
import Vue from "vue";
import { mapState } from "vuex";
import { MyTabbar } from "vue/types/vue";

@Component({
  name: "show",
  computed: {
    ...mapState({
      token: (state: any) => state.token,
    }),
  },
})
export default class Show extends Vue {
  //methods
  getImg(isShot: Boolean) {
    //图片选择方式

    //tmp
    isShot = false;

    if (isShot) {
      //拍照
      let cmr = plus.camera.getCamera();
      cmr.captureImage(
        (filePath) => {
          this.$api.upload(filePath, this.$parent, this.token);
        },
        (err) => {
          console.log(err.code, err.message);
        },
        {}
      );
    } else {
      //相册中选择
      plus.gallery.pick(
        (filePath) => {
          this.$api.upload(filePath, this.$parent, this.token);
        },
        (err) => {
          console.log(err.code, err.message);
        },
        { multiple: false, permissionAlert: true }
      );
    }
  }
  toSearch() {
    (this.$parent.$refs.tabbar as MyTabbar).redirect("search");
  }
}
</script>

<style scoped lang="scss">
#shot {
  header {
    position: absolute;
    left: 0;
    top: 0;
    width: 100vw;
    display: flex;
    align-items: center;
    justify-content: space-between;
    height: 8vh;
    .text {
      text-align: center;
      font-weight: bolder;
      font-size: 1.5rem;
      line-height: 8vh;
      flex-grow: 1;
      height: inherit;
    }
    .v-btn {
      height: 100%;
      flex-grow: 1;
      background-color: #64b5f6;
      color: #fff;
      // ::v-deep .v-btn__content {
      //   i {
      //     margin-right: 5%;
      //   }
      // }
    }
  }
  .warp {
    width: max-content;
    margin: 0 auto;
    display: flex;
    flex-direction: column;
    position: relative;
    #shotImg {
      height: auto;
      border-radius: 50%;
      padding: 40px;
      margin-bottom: 90px;
      z-index: 1;
    }
    .wave {
      position: absolute;
      width: 210.58px;
      height: 202px;
      border: 1px solid #64b5f6;
      border-radius: 50%;
      animation-name: wavesEffect;
      animation-duration: 1.5s;
      animation-iteration-count: infinite;
      animation-timing-function: ease-out;
    }
    @keyframes wavesEffect {
      from {
      }
      to {
        transform: scale(1.85, 1.85);
        opacity: 0;
      }
    }
    .searchLink {
      color: #fff;
      background-color: #81d4fa;
    }
  }
}
</style>
