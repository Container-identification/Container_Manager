package com.hanzoy.container_identification.mapper;

import com.hanzoy.container_identification.domain.Containersl;
import com.hanzoy.container_identification.domain.ContainerslExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ContainerslMapper {
    long countByExample(ContainerslExample example);

    int deleteByExample(ContainerslExample example);

    int deleteByPrimaryKey(Integer orders);

    int insert(Containersl record);

    int insertSelective(Containersl record);

    List<Containersl> selectByExample(ContainerslExample example);

    Containersl selectByPrimaryKey(Integer orders);

    int updateByExampleSelective(@Param("record") Containersl record, @Param("example") ContainerslExample example);

    int updateByExample(@Param("record") Containersl record, @Param("example") ContainerslExample example);

    int updateByPrimaryKeySelective(Containersl record);

    int updateByPrimaryKey(Containersl record);
}