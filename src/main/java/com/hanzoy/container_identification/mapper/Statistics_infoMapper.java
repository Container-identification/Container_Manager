package com.hanzoy.container_identification.mapper;

import com.hanzoy.container_identification.domain.Statistics_info;
import com.hanzoy.container_identification.domain.Statistics_infoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Statistics_infoMapper {
    long countByExample(Statistics_infoExample example);

    int deleteByExample(Statistics_infoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Statistics_info record);

    int insertSelective(Statistics_info record);

    List<Statistics_info> selectByExample(Statistics_infoExample example);

    Statistics_info selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Statistics_info record, @Param("example") Statistics_infoExample example);

    int updateByExample(@Param("record") Statistics_info record, @Param("example") Statistics_infoExample example);

    int updateByPrimaryKeySelective(Statistics_info record);

    int updateByPrimaryKey(Statistics_info record);
}