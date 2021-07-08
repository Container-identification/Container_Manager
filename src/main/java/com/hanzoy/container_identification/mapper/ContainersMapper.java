package com.hanzoy.container_identification.mapper;

import com.hanzoy.container_identification.domain.Containers;
import com.hanzoy.container_identification.domain.ContainersExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ContainersMapper {
    long countByExample(ContainersExample example);

    int deleteByExample(ContainersExample example);

    int deleteByPrimaryKey(Integer orders);

    int insert(Containers record);

    int insertSelective(Containers record);

    List<Containers> selectByExample(ContainersExample example);

    Containers selectByPrimaryKey(Integer orders);

    int updateByExampleSelective(@Param("record") Containers record, @Param("example") ContainersExample example);

    int updateByExample(@Param("record") Containers record, @Param("example") ContainersExample example);

    int updateByPrimaryKeySelective(Containers record);

    int updateByPrimaryKey(Containers record);
}