package com.muci.framework.auth.domain.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

/**
 * 用户角色表(SUserRole)表实体类
 *
 * @author makejava
 * @since 2024-08-21 10:16:58
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleSearchBO {

    private Set<Integer> userIds;

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
}
