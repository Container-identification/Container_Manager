<template>
  <div id="Info">
    <v-card>
      <v-list :flat="true">
        <v-subheader>分析信息</v-subheader>
        <v-list-item-group>
          <v-list-item>
            <v-list-item-content>
              <div class="header">msg1</div>
              <div class="body">{{ infos.msg1 }}</div>
            </v-list-item-content>
          </v-list-item>
          <v-list-item>
            <v-list-item-content>
              <div class="header">msg2</div>
              <div class="body">{{ infos.msg2 }}</div>
            </v-list-item-content>
          </v-list-item>
          <v-list-item>
            <v-list-item-content>
              <div class="header">msg3</div>
              <div class="body">{{ infos.msg3 }}</div>
            </v-list-item-content>
          </v-list-item>
        </v-list-item-group>
      </v-list>
    </v-card>
    <transition name="btn" :appear="true">
      <v-btn elevation="4" icon @click="dialogFlag = true" color="primary"
        ><v-icon size="60px">mdi-reply</v-icon></v-btn
      ></transition
    >
    <v-dialog v-model="dialogFlag" persistent>
      <v-card class="dialog">
        <v-card-title>是否返回主页</v-card-title>
        <v-card-actions>
          <v-btn @click="back(true)" text color="red darken-1">是</v-btn>
          <v-btn @click="back(false)" text color="green darken-1">否</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script lang="ts">
import { Component } from "vue-property-decorator";
import Vue from "vue";
import { mapState } from "vuex";

@Component({
  name: "info",
  computed: {
    ...mapState({
      infos: (state: any) => state.info.infos,
    }),
  },
})
export default class Info extends Vue {
  //data
  dialogFlag: boolean = false;

  //methods
  back(confrim: boolean) {
    this.dialogFlag = false;
    if (confrim) {
      this.$router.push("/");
      this.$store.commit("info/setMsg", {
        msg1: "",
        msg2: "",
        msg3: "",
      });
    }
  }
}
</script>

<style scoped lang="scss">
#Info {
  .v-card {
    width: 80vw;
    margin: 50px auto 0 auto;
    .v-list {
      .v-item-group {
        .v-list-item {
          .v-list-item__content {
            .header {
              flex: 1 1;
            }
            .body {
              flex: 3 3;
            }
          }
        }
      }
    }
  }
  .v-btn {
    margin: 100px auto 0 auto;
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
