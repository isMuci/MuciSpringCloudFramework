package com.muci.framework.auth.application.dto.request.menu;

import com.muci.framework.common.entity.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuSearchReq extends PageInfo {
    // 菜单id列表
    private List<Integer> menuIds;
    // 菜单名称
    private String menuName;
    // 路由地址
    private String path;
    // 请求类型
    private String method;
    // 权限表示
    private String perms;
    // 父菜单ID
    private List<Integer> parentIds;
    // 菜单类型;0菜单 1目录 2按钮
    private List<Integer> menuTypes;
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
