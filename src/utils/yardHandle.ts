let yardHandle: Function = (data: any[]): any => {
  const row = 8,
    col = 5;

  //数组中的最大order
  const MaxOrder = data.reduce((prev, cur) => {
    let now = parseInt(cur.order);
    return prev < now ? now : prev;
  }, 0);

  let res: any[][][] = [],
    yardNumber = Math.ceil(MaxOrder / (row * col)),
    yardTemplate = [
      //模板
      [undefined, undefined, undefined, undefined, undefined],
      [undefined, undefined, undefined, undefined, undefined],
      [undefined, undefined, undefined, undefined, undefined],
      [undefined, undefined, undefined, undefined, undefined],
      [undefined, undefined, undefined, undefined, undefined],
      [undefined, undefined, undefined, undefined, undefined],
      [undefined, undefined, undefined, undefined, undefined],
      [undefined, undefined, undefined, undefined, undefined],
    ];
  //构建堆场数组
  for (let i = 0; i < yardNumber; i++) {
    res.push(JSON.parse(JSON.stringify(yardTemplate)));
  }
  // //数据移入
  // let i = 0,
  //   j = 0,
  //   k = 0,
  //   m = 0;
  // while (i < length) {
  //   res[j][k][m] = data[i];
  //   if (++m >= col) {
  //     if (++k >= row) {
  //       j++;
  //       k = 0;
  //       m = 0;
  //     } else {
  //       m = 0;
  //     }
  //   }
  //   i++;
  // }

  //数据order处理，移入
  data.forEach((item) => {
    //位置计算
    let order = parseInt(item.order);
    //计算在第几分区
    let yardIndex = Math.floor(order / (row * col));
    order -= yardIndex * row * col;
    //计算第几行
    let yardRow = Math.floor(order / col);
    order %= col;
    //第几列
    let yardCol = order;

    res[yardIndex][yardRow][yardCol] = item;
  });
  // console.log(res);

  return res;
};

export default yardHandle;
