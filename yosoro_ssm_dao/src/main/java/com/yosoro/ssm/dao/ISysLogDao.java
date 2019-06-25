package com.yosoro.ssm.dao;

import com.yosoro.ssm.domain.SysLog;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ISysLogDao {
    // 查看日志
    @Select("select * from syslog")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "visitTime",column = "visitTime"),
            @Result(property = "username",column = "username"),
            @Result(property = "ip",column = "ip"),
            @Result(property = "url",column = "url"),
            @Result(property = "executionTime",column = "executionTime"),
            @Result(property = "method",column = "method")
    })
    public List<SysLog> findAll() throws Exception;

    // 添加日志
    @Insert("insert into syslog(visitTime,username,ip,url,executionTime,method) " +
            "values(#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    public void save(SysLog log) throws Exception;

    // 清空日志
    @Delete("truncate table syslog")
    public void truncateAllData() throws Exception;
}
