import upload from "./upload";
import signIn from "./signIn";
import search from "./search";
import queryYard from "./queryYard";
import identify from "./identify";
import operate from "./operate";
import deleteContainer from "./deleteContainer";
import yardStatistics from "./yardStatistics";

//初始化axios
import axios from "axios";
const config = require("@/config.json");
axios.defaults.baseURL = config.serverAddress;

export let api = {
  upload,
  signIn,
  search,
  queryYard,
  identify,
  operate,
  deleteContainer,
  yardStatistics,
};
