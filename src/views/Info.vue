<template>
  <div id="Info">
    <header>查询结果</header>

    <div class="warp">
      <info-content :infos="info"></info-content>
    </div>

    <transition name="btn" :appear="true">
      <v-btn elevation="4" icon @click="dialogFlag = true" color="primary"
        ><v-icon size="60px">fa-reply</v-icon></v-btn
      ></transition
    >

    <v-dialog v-model="dialogFlag" persistent>
      <v-card class="dialog">
        <v-card-title>是否返回</v-card-title>
        <v-card-actions>
          <v-btn @click="back(true)" text color="green darken-1">是</v-btn>
          <v-btn @click="back(false)" text color="red darken-1">否</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script lang="ts">
import { Component } from "vue-property-decorator";
import Vue from "vue";
import { mapState } from "vuex";
import InfoContent from "@/components/infoContent.vue";

@Component({
  name: "info",
  computed: {
    ...mapState({
      info: (state: any) => state.info.result,
    }),
  },
  components: {
    InfoContent,
  },
})
export default class Info extends Vue {
  //data
  dialogFlag: boolean = false;

  //methods
  back(confrim: boolean) {
    this.dialogFlag = false;
    if (confrim) {
      this.$router.go(-1);
    }
  }
}
</script>

<style scoped lang="scss">
#Info {
  height: 100vh;
  background-color: $homeBackground;
  header {
    font-weight: bolder;
    font-size: $headerFontSize;
    line-height: $headerHeight;
    padding-left: 15vw;
    @include myHeader;
  }
  .warp {
    max-height: 70vh;
    overflow: scroll;
    padding: 30px;
    border: thin solid rgba(0, 0, 0, 0.15);
    border-left: none;
    border-right: none;
    border-top: none;
  }
  .v-btn {
    margin: 50px auto 0 auto;
    display: block;
    width: 80px;
    height: 80px;
  }
  .btn-enter-active {
    animation-name: bounce;
    animation-duration: 1.75s;
  }
}
#app {
  .dialog {
    .v-card__title {
      display: flex;
      justify-content: center;
    }
    .v-card__actions {
      justify-content: space-around;
    }
  }
}
</style>
