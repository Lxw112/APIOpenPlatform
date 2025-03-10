package com.lxw.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lxw.lxwapicommon.model.entity.InterfaceInfo;
import com.lxw.lxwapicommon.model.entity.UserInterfaceInfo;
import com.lxw.project.annotation.AuthCheck;
import com.lxw.project.common.BaseResponse;
import com.lxw.project.common.ErrorCode;
import com.lxw.project.common.ResultUtils;
import com.lxw.project.exception.BusinessException;
import com.lxw.project.mapper.UserInterfaceInfoMapper;
import com.lxw.project.model.vo.InterfaceVO;
import com.lxw.project.service.InterfaceInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 分析控制器
 */
@RestController
@RequestMapping("/analysis")
@Slf4j
public class AnalysisController {

    @Resource
    private UserInterfaceInfoMapper userInterfaceInfoMapper;
    @Resource
    private InterfaceInfoService interfaceInfoService;
    @GetMapping("/top/interface/invoke")
    @AuthCheck(mustRole = "admin")
    public BaseResponse<List<InterfaceVO>> listTopInvokeInterface(){
        List<UserInterfaceInfo> userInterfaceInfoList = userInterfaceInfoMapper.listTopInvokeInterfaceInfo(3);
        Map<Long, List<UserInterfaceInfo>> interfaceInfoIdObjMap = userInterfaceInfoList.stream()
                .collect(Collectors.groupingBy(UserInterfaceInfo::getInterfaceInfoId));
        QueryWrapper<InterfaceInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id",interfaceInfoIdObjMap.keySet());
        List<InterfaceInfo> interfaceInfoList = interfaceInfoService.list(queryWrapper);
        if (CollectionUtils.isEmpty(interfaceInfoList)){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        List<InterfaceVO> interfaceVOList = interfaceInfoList.stream().map(interfaceInfo -> {
            InterfaceVO interfaceVO = new InterfaceVO();
            BeanUtils.copyProperties(interfaceInfo, interfaceVO);
            int totalNum = interfaceInfoIdObjMap.get(interfaceInfo.getId()).get(0).getTotalNum();
            interfaceVO.setTotalNum(totalNum);
            return interfaceVO;
        }).collect(Collectors.toList());
        return ResultUtils.success(interfaceVOList);
    }
}
