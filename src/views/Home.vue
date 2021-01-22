<template>
  <div class="home">
    <div class="warp">
      <v-btn
        id="shotImg"
        color="primary"
        elevation="5"
        outlined
        rounded
        @click="getImg(true)"
      >
        <v-icon size="120px">mdi-camera</v-icon>
      </v-btn>
      <div class="wave"></div>
      <v-btn
        color="primary"
        elevation="4"
        outlined
        id="pickImg"
        @click="getImg(false)"
        >从相册中选择</v-btn
      >
    </div>
    <loading :isShow="loading" :text="loadingText"></loading>
    <v-snackbar v-model="uploadFlag" :timeout="4000">{{
      uploadNotify
    }}</v-snackbar>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import loading from "@/components/loading.vue";

@Component({
  name: "home",
  components: {
    loading,
  },
})
export default class Home extends Vue {
  //data
  loadingText: String = "";
  loading: Boolean = false;
  uploadNotify: String = "";
  uploadFlag: Boolean = false;

  //methods
  getImg(isShot: Boolean) {
    //图片选择方式
    if (isShot) {
      //拍照
      let cmr = plus.camera.getCamera();
      cmr.captureImage(
        (filePath: String) => {
          this.$api.upload(filePath, this);
        },
        (err: any) => {
          console.log(err.code, err.message);
        },
        {}
      );
    } else {
      //相册中选择
      plus.gallery.pick(
        (filePath: String) => {
          this.$api.upload(filePath, this);
        },
        (err: any) => {
          console.log(err.code, err.message);
        },
        { multiple: false, permissionAlert: true }
      );
    }
  }

  //leftcycle
  mounted() {}
}
</script>

<style lang="scss" scoped>
.home {
  min-height: 70vh;
  padding-top: 100px;

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
      width: 202px;
      height: 202px;
      border: 1px solid #64b5f6;
      border-radius: 50%;
      animation-name: wavesEffect;
      animation-duration: 1.5s;
      animation-iteration-count: infinite;
    }
    @keyframes wavesEffect {
      from {
      }
      to {
        transform: scale(1.85, 1.85);
        opacity: 0;
      }
    }
  }
  .v-snack {
    text-align: center;
    ::v-deep.v-snack__wrapper {
      margin-bottom: 30px;
      .v-snack__content {
        text-align: center;
      }
    }
  }
}
</style>
