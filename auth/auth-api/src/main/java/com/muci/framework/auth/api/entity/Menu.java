package com.muci.framework.auth.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 菜单表(SMenu)表实体类
 *
 * @author makejava
 * @since 2024-08-21 10:16:56
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Menu {
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
}
