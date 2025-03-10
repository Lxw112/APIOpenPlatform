package com.lxw.lxwapicommon.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.lxw.lxwapicommon.model.entity.UserInterfaceInfo;

/**
* @author Administrator
* @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Service
* @createDate 2025-03-05 14:51:59
*/
public interface InnerUserInterfaceInfoService{

    /**
     * 调用接口统计
     * @param interfaceInfoId
     * @param userId
     * @return
     */
    boolean invokeCount(long interfaceInfoId,long userId);
}
