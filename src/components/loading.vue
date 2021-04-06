<template>
  <transition name="a">
    <div id="loading" v-if="isShow">
      <img v-if="isShowImg" :src="imgUrl" alt="" />
      <div class="container">
        <div class="preloader loading">
          <span class="slice"></span>
          <span class="slice"></span>
          <span class="slice"></span>
          <span class="slice"></span>
          <span class="slice"></span>
          <span class="slice"></span>
        </div>
        <div class="warp">
          <div class="text">{{ text }}</div>
          <div class="dots">
            <div
              class="dot"
              v-for="(i, index) in dot"
              :key="index"
              :style="{ '--delay': index * 100 + 'ms' }"
            >
              {{ i }}
            </div>
          </div>
        </div>
      </div>
    </div>
  </transition>
</template>

<script lang="ts">
import { Component } from "vue-property-decorator";
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
      default: "请稍后",
    },
    imgUrl: {
      type: String,
      default: "",
    },
  },
})
export default class Loading extends Vue {
  //data
  private dot: "."[] = [".", ".", ".", ".", ".", "."];
  public imgUrl: string = "";

  //computed
  get isShowImg() {
    return this.imgUrl != undefined && this.imgUrl != "";
  }

  //lifecycle
  created() {}
}
</script>

<style scoped lang="scss">
#loading {
  z-index: 999;
  height: 100vh;
  width: 100vw;
  position: fixed;
  top: 0;
  left: 0;
  display: flex;
  align-items: center;
  img {
    z-index: -1;
    position: absolute;
    max-height: 80vh;
    background-size: contain;
  }
  .container {
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    color: white;
    background-color: rgba(15, 17, 26, 0.5);
    height: 100%;
    width: 100%;
    .warp {
      margin-top: 50px;
      font-size: 1.5rem;
      letter-spacing: 4px;
      display: flex;
      .dots {
        display: flex;
        .dot {
          animation-duration: 1.2s;
          animation-fill-mode: both;
          transform-origin: center bottom;
          animation-name: dotBounce;
          animation-delay: var(--delay);
          animation-iteration-count: infinite;
        }
      }
    }
    @keyframes dotBounce {
      0%,
      50% {
        animation-timing-function: cubic-bezier(0.215, 0.61, 0.355, 1);
        // animation-timing-function: ease-out;
        transform: translate3d(0, 0, 0);
      }
      25% {
        animation-timing-function: cubic-bezier(0.755, 0.05, 0.855, 0.06);
        // animation-timing-function: ease-in;
        transform: translate3d(0, -1.5rem, 0);
      }
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
.a-enter-active {
  animation-name: zoomIn1;
  animation-duration: 0.4s;
}
.a-leave-active {
  animation-name: zoomOut1;
  animation-duration: 0.4s;
}
@keyframes zoomIn1 {
  from {
    opacity: 0;
    -webkit-transform: scale3d(0.3, 0.3, 0.3);
    transform: scale3d(0.3, 0.3, 0.3);
  }
  50% {
    opacity: 1;
  }
}
@keyframes zoomOut1 {
  from {
    opacity: 1;
  }
  50% {
    opacity: 0;
    -webkit-transform: scale3d(0.3, 0.3, 0.3);
    transform: scale3d(0.3, 0.3, 0.3);
  }
  to {
    opacity: 0;
  }
}
</style>
