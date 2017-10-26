﻿/**
 * @license
 * Copyright color-coding studio. All Rights Reserved.
 *
 * Use of this source code is governed by an Apache License, Version 2.0
 * that can be found in the LICENSE file at http://www.apache.org/licenses/LICENSE-2.0
 */

// 模块索引文件，此文件集中导出类
export * from "./OrganizationalStructure";
export * from "./Ownership";
export * from "./Role";

// 注册业务对象到工厂
import * as ibas from "ibas/index";
import { OrganizationalStructure } from "./OrganizationalStructure";
ibas.boFactory.register(OrganizationalStructure.BUSINESS_OBJECT_CODE, OrganizationalStructure);
import { Ownership } from "./Ownership";
ibas.boFactory.register(Ownership.BUSINESS_OBJECT_CODE, Ownership);
import { Role } from "./Role";
ibas.boFactory.register(Role.BUSINESS_OBJECT_CODE, Role);
