package com.muci.framework.auth.domain.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
public class MenuBO {
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
    // 备注
    private String remark;
}