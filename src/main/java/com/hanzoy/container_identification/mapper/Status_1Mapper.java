package com.hanzoy.container_identification.mapper;

import com.hanzoy.container_identification.domain.Status_1;
import com.hanzoy.container_identification.domain.Status_1Example;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Status_1Mapper {
    long countByExample(Status_1Example example);

    int deleteByExample(Status_1Example example);

    int deleteByPrimaryKey(Integer id);

    int insert(Status_1 record);

    int insertSelective(Status_1 record);

    List<Status_1> selectByExample(Status_1Example example);

    Status_1 selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Status_1 record, @Param("example") Status_1Example example);

    int updateByExample(@Param("record") Status_1 record, @Param("example") Status_1Example example);

    int updateByPrimaryKeySelective(Status_1 record);

    int updateByPrimaryKey(Status_1 record);
}