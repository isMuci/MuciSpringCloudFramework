package com.muci.framework.auth.application.dto.request.userRole;

import com.muci.framework.common.entity.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleSearchReq extends PageInfo {
    // 用户ID列表
    private Set<Integer> userIds;
    // 角色ID列表
    private Set<Integer> roleIds;
    // 创建者
    private Integer createBy;
    // 起始创建时间
    private LocalDate startCreate;
    // 结束创建时间
    private LocalDate endCreate;
    // 更新者
    private Integer updateBy;
    // 起始更新时间
    private LocalDate startUpdate;
    // 结束更新时间
    private LocalDate endUpdate;
    // 页码
    private Integer page;
    // 每页数量
    private Integer limit;
}
