package com.lxw.project.model.vo;

import com.lxw.lxwapicommon.model.entity.InterfaceInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class InterfaceVO extends InterfaceInfo {

    /**
     * 调用次数
     */

    private Integer totalNum;
}
