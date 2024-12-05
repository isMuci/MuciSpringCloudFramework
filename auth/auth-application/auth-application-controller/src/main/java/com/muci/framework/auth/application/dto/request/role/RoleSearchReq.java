package com.muci.framework.auth.application.dto.request.role;

import com.muci.framework.common.entity.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

/**
 * 角色表(SRole)表实体类
 *
 * @author makejava
 * @since 2024-08-21 10:16:57
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleSearchReq extends PageInfo {
    // 用户id列表
    private List<Integer> roleIds;
    // 角色名称
    private String roleName;
    // 角色权限
    private String roleKey;
    // 状态
    private Integer status;
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
    // 备注
    // private String remark;
    // 页码
    private Integer page;
    // 每页数量
    private Integer limit;
}
