package com.hanzoy.container_identification.mapper;

import com.hanzoy.container_identification.domain.Statistics_sort;
import com.hanzoy.container_identification.domain.Statistics_sortExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Statistics_sortMapper {
    long countByExample(Statistics_sortExample example);

    int deleteByExample(Statistics_sortExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Statistics_sort record);

    int insertSelective(Statistics_sort record);

    List<Statistics_sort> selectByExample(Statistics_sortExample example);

    Statistics_sort selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Statistics_sort record, @Param("example") Statistics_sortExample example);

    int updateByExample(@Param("record") Statistics_sort record, @Param("example") Statistics_sortExample example);

    int updateByPrimaryKeySelective(Statistics_sort record);

    int updateByPrimaryKey(Statistics_sort record);
}