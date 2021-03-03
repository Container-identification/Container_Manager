<template>
  <div id="history">
    <v-alert type="info">查看已搜索的货箱号</v-alert>
    <v-expansion-panels v-show="hasRes">
      <v-expansion-panel>
        <v-expansion-panel-header
          >箱号：{{ infos.number }}</v-expansion-panel-header
        >
        <v-expansion-panel-content>
          <info-content :infos="infos"></info-content>
        </v-expansion-panel-content>
      </v-expansion-panel>
    </v-expansion-panels>
  </div>
</template>

<script lang="ts">
import { Component } from "vue-property-decorator";
import Vue from "vue";
import { mapState } from "vuex";
import InfoContent from "@/components/infoContent.vue";

@Component({
  name: "history",
  computed: {
    ...mapState({
      token: (state: any) => state.token,
      infos: (state: any) => state.info.infos,
    }),
  },
  components: {
    InfoContent,
  },
})
export default class History extends Vue {
  //data
  hasRes = false;

  //lifeCycle
  created() {
    let data = {
      token: this.token,
    };
    this.$api.history(data, this.$parent, this);
  }
}
</script>

<style scoped lang="scss">
#history {
  align-self: flex-start;
  margin-top: 100px;
  .v-alert {
    position: absolute;
    left: 0;
    top: 0;
    width: 100vw;
  }
  .v-expansion-panels {
    max-width: 90vw;
    margin: 0 auto;
    .v-expansion-panel-content {
      height: 60vh;
      overflow: scroll;
    }
  }
}
</style>
