package com.lxw.lxwinterface.mapper;

import com.lxw.lxwinterface.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Administrator
* @description 针对表【user(用户)】的数据库操作Mapper
* @createDate 2025-03-01 21:48:41
* @Entity com.lxw.lxwinterface.entity.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




