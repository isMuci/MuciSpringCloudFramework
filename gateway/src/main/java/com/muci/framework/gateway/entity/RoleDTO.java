package com.muci.framework.gateway.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
public class RoleDTO {
    // 角色ID
    private Integer roleId;
    // 角色名称
    private String roleName;
    // 角色权限
    private String roleKey;
    // 状态
    private Integer status;
    // 创建者
    private Integer createBy;
    // 创建时间
    private Date createAt;
    // 更新者
    private Integer updateBy;
    // 更新时间
    private Date updateAt;
    // 备注
    private String remark;
}
