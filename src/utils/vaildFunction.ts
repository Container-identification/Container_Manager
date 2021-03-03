let isNothing: Function = (
  value: number | string | undefined | null
): boolean => {
  return value == undefined || value == "";
};

export default { isNothing };
