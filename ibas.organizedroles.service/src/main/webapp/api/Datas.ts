/**
 * @license
 * Copyright color-coding studio. All Rights Reserved.
 *
 * Use of this source code is governed by an Apache License, Version 2.0
 * that can be found in the LICENSE file at http://www.apache.org/licenses/LICENSE-2.0
 */

// 共享的数据
import {
    strings,
    MODULE_REPOSITORY_NAME_TEMPLATE,
} from "ibas/index";

/** 模块-标识 */
export const CONSOLE_ID: string = "18199a3e-29a7-4d43-ab4b-4de5454eb0a4";
/** 模块-名称 */
export const CONSOLE_NAME: string = "OrganizedRoles";
/** 模块-版本 */
export const CONSOLE_VERSION: string = "0.1.0";
/** 业务仓库名称 */
export const BO_REPOSITORY_ORGANIZEDROLES: string = strings.format(MODULE_REPOSITORY_NAME_TEMPLATE, CONSOLE_NAME);
/** 业务对象编码-组织-结构 */
export const BO_CODE_ORGANIZATIONALSTRUCTURE: string = "${Company}_OR_ORG_STRUCTURE";
/** 业务对象编码-数据权限 */
export const BO_CODE_OWNERSHIP: string = "${Company}_OR_OWNERSHIP";
/** 业务对象编码-角色 */
export const BO_CODE_ROLE: string = "${Company}_OR_ROLE";
