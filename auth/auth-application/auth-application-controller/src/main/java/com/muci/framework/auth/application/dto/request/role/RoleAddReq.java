package com.muci.framework.auth.application.dto.request.role;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class RoleAddReq {
    // 角色名称
    @NotBlank(message = "角色名称不能为空")
    private String roleName;
    // 角色权限
    @NotBlank(message = "角色键值不能为空")
    private String roleKey;
    // 备注
    // private String remark;
}
