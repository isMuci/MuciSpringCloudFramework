package com.muci.framework.auth.application.dto.response.menu;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuRes {
    // 菜单ID
    private Integer menuId;
    // 菜单名称
    private String menuName;
    // 路由地址
    private String path;
    // 请求类型
    private String method;
    // 权限表示
    private String perms;
    // 父菜单ID
    private Integer parentId;
    // 菜单类型;0菜单 1目录 2按钮
    private Integer menuType;
    // 状态
    private Integer status;
    // 创建者
    private Integer createBy;
    // 创建时间
    private LocalDateTime createAt;
    // 更新者
    private Integer updateBy;
    // 更新时间
    private LocalDateTime updateAt;
    // 备注
    private String remark;
}
