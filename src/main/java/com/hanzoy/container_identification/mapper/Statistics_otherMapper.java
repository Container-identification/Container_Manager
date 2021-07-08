package com.hanzoy.container_identification.mapper;

import com.hanzoy.container_identification.domain.Statistics_other;
import com.hanzoy.container_identification.domain.Statistics_otherExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Statistics_otherMapper {
    long countByExample(Statistics_otherExample example);

    int deleteByExample(Statistics_otherExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Statistics_other record);

    int insertSelective(Statistics_other record);

    List<Statistics_other> selectByExample(Statistics_otherExample example);

    Statistics_other selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Statistics_other record, @Param("example") Statistics_otherExample example);

    int updateByExample(@Param("record") Statistics_other record, @Param("example") Statistics_otherExample example);

    int updateByPrimaryKeySelective(Statistics_other record);

    int updateByPrimaryKey(Statistics_other record);
}