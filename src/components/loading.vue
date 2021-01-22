<template>
  <div id="loading">
    <transition name="a">
      <div class="warp" v-if="isShow">
        <div class="preloader loading">
          <span class="slice"></span>
          <span class="slice"></span>
          <span class="slice"></span>
          <span class="slice"></span>
          <span class="slice"></span>
          <span class="slice"></span>
        </div>
        <p>{{ text === "" ? "加载中" + this.dot : text + this.dot }}</p>
      </div>
    </transition>
  </div>
</template>

<script lang="ts">
import { Component, Prop } from "vue-property-decorator";
import Vue from "vue";

@Component({
  name: "loading",
  props: {
    isShow: {
      type: Boolean,
      default: false,
    },
    text: {
      type: String,
      default: "",
    },
  },
})
export default class Loading extends Vue {
  //data
  timer: number | undefined;
  dot: string = "...";

  //lifecycle
  created() {
    this.timer = setInterval(() => {
      if (this.dot.length >= 3) {
        this.dot = this.dot.substr(0, 1);
      } else {
        this.dot = this.dot + ".";
      }
    }, 750);
  }
  beforeDestroyed() {
    clearInterval(this.timer);
  }
}
</script>

<style scoped lang="scss">
#loading {
  .a-enter-active {
    animation-name: zoomIn;
    animation-duration: 0.4s;
  }
  .a-leave-active {
    animation-name: zoomOut;
    animation-duration: 0.4s;
  }
  .warp {
    z-index: 999;
    height: 100vh;
    width: 100vw;
    position: fixed;
    top: 0;
    left: 0;
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    color: white;
    background-color: rgba(15, 17, 26, 0.75);

    p {
      margin-top: 50px;
      font-size: 1.5em;
      letter-spacing: 4px;
    }

    //codepen https://codepen.io/mrrocks/pen/EiplA
    $fill: #4834d4;
    $fade: #686de0;

    @for $i from 1 through 6 {
      @keyframes preload-show-#{$i} {
        from {
          transform: rotateZ(60 * $i + deg) rotateY(-90deg) rotateX(0deg);
          border-left-color: $fill;
        }
      }
      @keyframes preload-hide-#{$i} {
        to {
          transform: rotateZ(60 * $i + deg) rotateY(-90deg) rotateX(0deg);
          border-left-color: $fill;
        }
      }

      @keyframes preload-cycle-#{$i} {
        $startIndex: $i * 5;
        $reverseIndex: (80 - $i * 5);

        #{$startIndex * 1%} {
          transform: rotateZ(60 * $i + deg) rotateY(90deg) rotateX(0deg);
          border-left-color: $fill;
        }
        #{$startIndex + 5%},
        #{$reverseIndex * 1%} {
          transform: rotateZ(60 * $i + deg) rotateY(0) rotateX(0deg);
          border-left-color: $fade;
        }

        #{$reverseIndex + 5%},
        100% {
          transform: rotateZ(60 * $i + deg) rotateY(90deg) rotateX(0deg);
          border-left-color: $fill;
        }
      }
    }

    @keyframes preload-flip {
      0% {
        transform: rotateY(0deg) rotateZ(-60deg);
      }
      100% {
        transform: rotateY(360deg) rotateZ(-60deg);
      }
    }

    body {
      background: #efefef;
    }

    .preloader {
      position: absolute;
      top: 40%;
      left: 50%;
      font-size: 20px;
      display: block;
      width: 3.75em;
      height: 4.25em;
      margin-left: -1.875em;
      margin-top: -2.125em;
      transform-origin: center center;
      transform: rotateY(180deg) rotateZ(-60deg);

      .slice {
        border-top: 1.125em solid transparent;
        border-right: none;
        border-bottom: 1em solid transparent;
        border-left: 1.875em solid$fade;
        position: absolute;
        top: 0px;
        left: 50%;
        transform-origin: left bottom;
        border-radius: 3px 3px 0 0;
      }

      @for $i from 1 through 6 {
        .slice:nth-child(#{$i}) {
          transform: rotateZ(60 * $i + deg) rotateY(0deg) rotateX(0);
          animation: 0.15s linear 0.9 - $i * 0.08s preload-hide-#{$i} both 1;
        }
      }

      &.loading {
        animation: 2s preload-flip steps(2) infinite both;
        @for $i from 1 through 6 {
          .slice:nth-child(#{$i}) {
            transform: rotateZ(60 * $i + deg) rotateY(90deg) rotateX(0);
            animation: 2s preload-cycle-#{$i} linear infinite both;
          }
        }
      }
    }
  }
}
</style>
