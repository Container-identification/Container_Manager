<template>
  <div id="search">
    <header>箱号查询</header>
    <v-text-field
      label="搜索指定货箱号"
      prepend-icon="fa-search"
      v-model="container"
    >
      <template #append-outer>
        <v-btn @click="search">查询</v-btn>
      </template>
    </v-text-field>
    <transition name="infoShow">
      <v-expansion-panels>
        <v-expansion-panel v-for="item in infos" :key="item.name">
          <v-expansion-panel-header
            >箱号：{{ item.number }}</v-expansion-panel-header
          >
          <v-expansion-panel-content>
            <info-content :infos="item"></info-content>
          </v-expansion-panel-content>
        </v-expansion-panel>
      </v-expansion-panels>
    </transition>
  </div>
</template>

<script lang="ts">
import { Component } from "vue-property-decorator";
import Vue from "vue";
import { mapState } from "vuex";
import InfoContent from "@/components/infoContent.vue";

@Component({
  name: "search",
  computed: {
    ...mapState({
      token: (state: any) => state.token,
      infos: (state: any) => state.info.manyInfos,
    }),
  },
  components: {
    InfoContent,
  },
})
export default class Search extends Vue {
  //data
  container = "";

  //methods
  search() {
    let data = {
      token: this.token,
      container: this.container,
    };
    this.$api.search(data, this.$parent, this);
  }
}
</script>

<style scoped lang="scss">
#search {
  margin-top: 8vh;
  align-self: flex-start;
  header {
    position: absolute;
    left: 15vw;
    top: 1vh;
    font-weight: bolder;
    font-size: 1.5rem;
    z-index: 2;
  }
  .infoShow-enter-active {
    animation-name: fadeInUp;
    animation-duration: 1s;
  }
  .v-expansion-panels {
    max-width: 85vw;
    margin: 0 auto 100px auto;
    .v-expansion-panel-content {
      max-height: 60vh;
      overflow: scroll;
    }
  }
}
</style>
