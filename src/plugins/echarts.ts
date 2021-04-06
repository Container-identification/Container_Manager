import * as echarts from "echarts/core";
import { PieChart, PieSeriesOption } from "echarts/charts";
import { CanvasRenderer } from "echarts/renderers";

echarts.use([PieChart, CanvasRenderer]);

export default echarts;
export type ESOption = echarts.ComposeOption<PieSeriesOption>;
