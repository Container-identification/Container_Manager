package com.hanzoy.container_identification.mapper;

import com.hanzoy.container_identification.domain.Sta;
import com.hanzoy.container_identification.domain.StaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StaMapper {
    long countByExample(StaExample example);

    int deleteByExample(StaExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Sta record);

    int insertSelective(Sta record);

    List<Sta> selectByExample(StaExample example);

    Sta selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Sta record, @Param("example") StaExample example);

    int updateByExample(@Param("record") Sta record, @Param("example") StaExample example);

    int updateByPrimaryKeySelective(Sta record);

    int updateByPrimaryKey(Sta record);
}