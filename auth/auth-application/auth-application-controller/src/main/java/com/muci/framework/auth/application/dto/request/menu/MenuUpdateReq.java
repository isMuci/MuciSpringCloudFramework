package com.muci.framework.auth.application.dto.request.menu;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuUpdateReq {
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
}
