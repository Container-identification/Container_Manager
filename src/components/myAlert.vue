<template>
  <div id="myAlert">
    <v-alert
      :type="type"
      transition="scroll-y-transition"
      outlined
      :value="show"
      dense
      text
      >{{ text }}</v-alert
    >
  </div>
</template>

<script lang="ts">
import { Component } from "vue-property-decorator";
import Vue from "vue";

@Component({
  name: "myAlert",
})
export default class MyAlert extends Vue {
  //data
  show = false;
  type = "info";
  text = "...";

  //methods
  showUp(
    duration: number,
    type: "success" | "info" | "error" | "warning",
    text?: string
  ) {
    this.show = true;
    this.type = type;
    this.text = text ? text : "...";
    setTimeout(() => {
      this.show = false;
    }, duration);
  }
}
</script>

<style scoped lang="scss">
#myAlert {
  position: absolute;
  top: 120px;
  left: 50%;
  transform: translateX(-50%);
  z-index: 999;
  .v-alert {
    background-color: rgba(#fff, 0.85) !important;
  }
}
</style>
