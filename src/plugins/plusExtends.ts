interface plusReadyFunction {
  (event: "plusready", fn: Function, isCatch: boolean): void;
}

const plusExtends = (fn: Function) => {
  if (window.plus) {
    setTimeout(fn, 0);
  } else {
    (document.addEventListener as plusReadyFunction)("plusready", fn, false);
  }
};

export default plusExtends;
