//root
export interface myRootState {
  token: string;
  tmpIndex: number;
}

// history
export interface histroyModule {
  count: number;
  list: string[];
}

//info
export interface infoModule {
  result: infoData | undefined;
}

export interface infoData {
  container: string;
  owner: string;
  status: number;
  type: string;
  tare: string;
  payLoad: string;
  table: (transitInfo | manageInfo)[];
}

interface transitInfo {
  operate: string;
  place: string;
  date: string;
  time: string;
  method: string;
  voyage: string;
}

interface manageInfo {
  operate: string;
  place: string;
  date: string;
  time: string;
  person: string;
  remark?: string;
}

//yard
export interface yardModule {
  //tmp
  data: any[];
  yardIndex: number;
  isAllYard: boolean;
  yardArray: yardArray;
  sta: containerStatus[];
}

export type yardArray = aYard[];

type aYard = [
  //8行
  aRowYard,
  aRowYard,
  aRowYard,
  aRowYard,
  aRowYard,
  aRowYard,
  aRowYard,
  aRowYard
];

type YardRow = [
  //5列
  containerInfo,
  containerInfo,
  containerInfo,
  containerInfo,
  containerInfo
];

interface containerInfo {
  order: string;
  sta: number;
  container: string;
  owner: string;
  status: number;
  type: string;
  tare: string;
  payLoad: string;
  url?: string;
}

interface containerStatus {
  id: string;
  content: string;
}
