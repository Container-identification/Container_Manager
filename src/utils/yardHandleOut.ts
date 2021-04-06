let yardHandleOut: Function = (order: locate) => {
  const row = 5,
    col = 8,
    page = row * col;

  let res = order.pageData * page + order.row * row + order.col + 1;
  console.log(order.pageData, order.row, order.col, res);
  return res;
};

interface locate {
  row: number;
  col: number;
  pageData: number;
}

export default yardHandleOut;
