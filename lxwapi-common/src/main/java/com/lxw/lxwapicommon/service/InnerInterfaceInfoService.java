package com.lxw.lxwapicommon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lxw.lxwapicommon.model.entity.InterfaceInfo;



/**
* @author Administrator
* @description 针对表【interface_info(接口信息)】的数据库操作Service
* @createDate 2025-02-24 21:37:38
*/
public interface InnerInterfaceInfoService{

    /**
     * 从数据库里查询模拟接口是否存在（请求路径，请求方法）
     * @param path
     * @param method
     * @return
     */
    InterfaceInfo getInterfaceInfo(String path, String method);
}
