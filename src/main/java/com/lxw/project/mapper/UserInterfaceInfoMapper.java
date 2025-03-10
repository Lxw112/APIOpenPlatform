package com.lxw.project.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lxw.lxwapicommon.model.entity.UserInterfaceInfo;

import java.util.List;

/**
* @author Administrator
* @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Mapper
* @createDate 2025-03-05 14:51:59
* @Entity com.lxw.project.model.entity.UserInterfaceInfo
*/
public interface UserInterfaceInfoMapper extends BaseMapper<UserInterfaceInfo> {

    List<UserInterfaceInfo> listTopInvokeInterfaceInfo(int limit);
}




