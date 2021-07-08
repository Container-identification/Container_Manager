package com.hanzoy.container_identification.service.impl;

import com.hanzoy.container_identification.domain.*;
import com.hanzoy.container_identification.dto.CommonResult;
import com.hanzoy.container_identification.dto.param.DeleteParam;
import com.hanzoy.container_identification.dto.param.OperateParam;
import com.hanzoy.container_identification.dto.vo.StatistiscInfoVO;
import com.hanzoy.container_identification.mapper.*;
import com.hanzoy.container_identification.service.YardService;
import com.hanzoy.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class YardServiceImpl implements YardService {

    @Resource
    StaMapper staMapper;

    @Resource
    ContainersMapper containerMapper;

    @Resource
    ContainerslMapper containerslMapper;

    @Resource
    Statistics_infoMapper statistics_infoMapper;

    @Resource
    Statistics_sortMapper statistics_sortMapper;

    @Resource
    Statistics_otherMapper statistics_otherMapper;

    @Autowired
    JWTUtils jwtUtils;

    @Resource
    MyStatusMapper status1Mapper;

    @Override
    public CommonResult queryYard() {
        StaExample staExample = new StaExample();
        staExample.createCriteria();
        List<Sta> sta = staMapper.selectByExample(staExample);

        ContainersExample containerExample = new ContainersExample();
        containerExample.createCriteria();
        List<Containers> info = containerMapper.selectByExample(containerExample);

        HashMap<String, Object> data = new HashMap<>();
        data.put("sta", sta);
        data.put("info", info);
        return CommonResult.success(data);
    }

    @Override
    public CommonResult yardStatistics() {

        ContainersExample containersExample = new ContainersExample();
        containersExample.createCriteria();
        List<Containers> containers = containerMapper.selectByExample(containersExample);
        int needXiu = 0;
        for (Containers container : containers) {
            if(container.getSta() == 1){
                needXiu++;
            }
        }

        Statistics_infoExample statistics_infoExample = new Statistics_infoExample();

        statistics_infoExample.createCriteria();


        StatistiscInfoVO statistiscInfoVO = new StatistiscInfoVO();
        ArrayList<StatistiscInfoVO.Info> infos = new ArrayList<>();



        List<Statistics_info> statistics_infos = statistics_infoMapper.selectByExample(statistics_infoExample);
        for (Statistics_info statistics_info : statistics_infos) {
            StatistiscInfoVO.Info info = new StatistiscInfoVO.Info();
            info.setTotal(statistics_info.getTotal().toString());
            info.setTotalWeight(statistics_info.getTotalweight().toString());
            info.setUsedWeight(statistics_info.getUsedweight().toString());

            Statistics_otherExample statistics_otherExample = new Statistics_otherExample();
            Statistics_sortExample statistics_sortExample = new Statistics_sortExample();

            statistics_sortExample.createCriteria()
                    .andInfoIdEqualTo(statistics_info.getId());
            statistics_otherExample.createCriteria()
                    .andInfoIdEqualTo(statistics_info.getId());

            List<Statistics_sort> statistics_sorts = statistics_sortMapper.selectByExample(statistics_sortExample);
            List<Statistics_other> statistics_others = statistics_otherMapper.selectByExample(statistics_otherExample);
            ArrayList<StatistiscInfoVO.Info.Sort> set_sort = new ArrayList<>();
            ArrayList<StatistiscInfoVO.Info.Other> set_other = new ArrayList<>();
            for (Statistics_sort statistics_sort : statistics_sorts) {
                StatistiscInfoVO.Info.Sort sort = new StatistiscInfoVO.Info.Sort();
                sort.setName(statistics_sort.getName());
                sort.setCount(statistics_sort.getCount().toString());
                set_sort.add(sort);
            }
            for (Statistics_other statistics_other : statistics_others) {
                StatistiscInfoVO.Info.Other other = new StatistiscInfoVO.Info.Other();
                other.setName(statistics_other.getName());
                other.setContent(statistics_other.getContent());
                set_other.add(other);
            }
            info.setOther(set_other);
            info.setSort(set_sort);
            infos.add(info);
        }
        statistiscInfoVO.setInfo(infos);

        statistiscInfoVO.getInfo().get(0).getOther().get(0).setContent((String.valueOf(needXiu)));
        return CommonResult.success(statistiscInfoVO);
    }

    @Override
    public CommonResult operate(OperateParam param) {
        Containers containers = new Containers();

        ContainerslExample containerslExample = new ContainerslExample();
        containerslExample.createCriteria()
                .andContainerEqualTo(param.getContainer());
        List<Containersl> containersls = containerslMapper.selectByExample(containerslExample);
        String url = "";
        if(!containersls.isEmpty()){
            url = containersls.get(0).getUrl();
        }

        containers.setContainer(param.getContainer());
        containers.setOrders(new Integer(param.getOrder()));
        containers.setOwner(param.getOwner());
        containers.setPayload(param.getPayLoad());
        containers.setSta(0);
        containers.setStatus(1);
        containers.setType(param.getType());
        containers.setTare(param.getTare());
        containers.setUrl(url);

        Containers containers1 = containerMapper.selectByPrimaryKey(containers.getOrders());
        if(containers1 != null){
            return CommonResult.fail("该位置已存在集装箱");
        }

        containerMapper.insert(containers);

        Status_1 status = new Status_1();

        status.setContainer(param.getContainer());
        status.setOperate("入库");
        status.setPlace("堆场中");

        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
        sdf.applyPattern("yyyy-MM-dd");// a为am/pm的标记

        Date date = new Date();// 获取当前时间

        status.setDate(sdf.format(date));
        sdf.applyPattern("HH:mm:ss");// a为am/pm的标记
        status.setTime(sdf.format(date));
        status.setPerson((String) jwtUtils.getValueFromToken(param.getToken(), "username"));
        status.setRemark("");

        status1Mapper.insertStatus(status);

        return CommonResult.success();
    }

    @Override
    public CommonResult delete(DeleteParam param) {
        String container = status1Mapper.selectConByOrder(param.getOrder());
        containerMapper.deleteByPrimaryKey(new Integer(param.getOrder()));

        Status_1 status = new Status_1();

        status.setContainer(container);
        status.setOperate("出库");
        status.setPlace("已出库");

        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
        sdf.applyPattern("yyyy-MM-dd");// a为am/pm的标记

        Date date = new Date();// 获取当前时间

        status.setDate(sdf.format(date));
        sdf.applyPattern("HH:mm:ss");// a为am/pm的标记
        status.setTime(sdf.format(date));
        status.setPerson((String) jwtUtils.getValueFromToken(param.getToken(), "username"));
        status.setRemark("");

        status1Mapper.insertStatus(status);
        return CommonResult.success();
    }
}
