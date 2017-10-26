/**
 * @license
 * Copyright color-coding studio. All Rights Reserved.
 *
 * Use of this source code is governed by an Apache License, Version 2.0
 * that can be found in the LICENSE file at http://www.apache.org/licenses/LICENSE-2.0
 */

import {
    FetchCaller,
    SaveCaller
} from "ibas/index";
import * as bo from "./bo/index"

/** 业务仓库 */
export interface IBORepositoryOrganizedRoles {

    /**
     * 查询 组织-结构
     * @param fetcher 查询者
     */
    fetchOrganizationalStructure(fetcher: FetchCaller<bo.IOrganizationalStructure>);
    /**
     * 保存 组织-结构
     * @param saver 保存者
     */
    saveOrganizationalStructure(saver: SaveCaller<bo.IOrganizationalStructure>);

    /**
     * 查询 数据权限
     * @param fetcher 查询者
     */
    fetchOwnership(fetcher: FetchCaller<bo.IOwnership>);
    /**
     * 保存 数据权限
     * @param saver 保存者
     */
    saveOwnership(saver: SaveCaller<bo.IOwnership>);

    /**
     * 查询 角色
     * @param fetcher 查询者
     */
    fetchRole(fetcher: FetchCaller<bo.IRole>);
    /**
     * 保存 角色
     * @param saver 保存者
     */
    saveRole(saver: SaveCaller<bo.IRole>);


}
