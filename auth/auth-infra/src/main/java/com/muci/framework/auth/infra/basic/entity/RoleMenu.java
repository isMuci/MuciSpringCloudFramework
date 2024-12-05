package com.muci.framework.auth.infra.basic.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("s_role_menu")
public class RoleMenu extends Model<RoleMenu> {
    // 角色ID
    private Integer roleId;
    // 菜单ID
    private Integer menuId;
    // 删除标志;0正常 1删除
    private Integer isDel;
    // 创建者
    private Integer createBy;
    // 创建时间
    private LocalDateTime createAt;
    // 更新者
    private Integer updateBy;
    // 更新时间
    private LocalDateTime updateAt;
}
