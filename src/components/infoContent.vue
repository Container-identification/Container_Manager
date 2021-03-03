<template>
  <div id="infoContent">
    <v-divider></v-divider>
    <div class="header">
      <header>集装箱信息</header>
      <div class="content">
        <span class="key">集装箱号：</span>
        <span class="value">{{ infos.number }}</span>
      </div>
      <div class="content">
        <span class="key">箱主：</span>
        <span class="value">{{ infos.owner }}</span>
      </div>
      <div class="content">
        <span class="key">状态：</span>
        <span class="value">{{ infos.status }}</span>
      </div>
      <div class="content">
        <span class="key">箱型：</span
        ><span class="value">{{ infos.type }}</span>
      </div>
      <!-- <div class="content">
        <span class="key">描述：</span
        ><span class="value">{{ infos.description }}</span>
      </div>
      <div class="content">
        <span class="key">尺寸：</span
        ><span class="value">{{ infos.size }}</span>
      </div> -->
      <div class="content">
        <span class="key">Tare (kg)：</span
        ><span class="value">{{ infos.tare }}</span>
      </div>
      <div class="content">
        <span class="key">Max.Payload (kg)：</span
        ><span class="value">{{ infos.payload }}</span>
      </div>
    </div>

    <v-divider></v-divider>

    <v-simple-table v-if="infos.outer">
      <!-- 外部 -->
      <thead>
        <tr>
          <th>操作</th>
          <th>操作地点</th>
          <th>日期</th>
          <th>时间</th>
          <th>运输方式</th>
          <th>航次</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="item in infos.action" :key="item.name">
          <td>{{ item.name }}</td>
          <td>{{ item.place }}</td>
          <td>{{ item.date }}</td>
          <td>{{ item.time }}</td>
          <td>{{ item.way }}</td>
          <td>{{ item.voyage }}</td>
        </tr>
      </tbody>
    </v-simple-table>

    <v-simple-table v-else>
      <!-- 内部 -->
      <thead>
        <tr>
          <th>操作</th>
          <th>操作地点</th>
          <th>日期</th>
          <th>时间</th>
          <th>运输员</th>
          <th>备注</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="item in infos.action" :key="item.name">
          <td>{{ item.name }}</td>
          <td>{{ item.place }}</td>
          <td>{{ item.date }}</td>
          <td>{{ item.time }}</td>
          <td>{{ item.person }}</td>
          <td>{{ item.remark }}</td>
        </tr>
      </tbody>
    </v-simple-table>
    <v-divider></v-divider>
  </div>
</template>

<script lang="ts">
import { Component } from "vue-property-decorator";
import Vue from "vue";

@Component({
  name: "infoContent",
  props: {
    infos: {
      type: Object,
      required: true,
    },
  },
})
export default class InfoContent extends Vue {}
</script>

<style scoped lang="scss">
#infoContent {
  :nth-child(-n + 4) {
    header {
      font-size: 18px;
      font-weight: bolder;
      padding: 10px 0;
    }
    :last-child {
      padding-bottom: 10px;
    }
  }
  .header {
    .key {
      font-weight: bold;
    }
  }
  th {
    white-space: nowrap;
  }
  td {
    white-space: nowrap;
  }
}
</style>
