package com.lds.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lds.pojo.po.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
