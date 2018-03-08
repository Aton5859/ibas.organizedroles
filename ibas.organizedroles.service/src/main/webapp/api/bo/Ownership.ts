/**
 * @license
 * Copyright Color-Coding Studio. All Rights Reserved.
 *
 * Use of this source code is governed by an Apache License, Version 2.0
 * that can be found in the LICENSE file at http://www.apache.org/licenses/LICENSE-2.0
 */
namespace organizedroles {
    export namespace bo {

        /** 数据权限 */
        export interface IOwnership extends ibas.IBOSimple {

            /** 用户编码 */
            userCode: string;

            /** 对象编码 */
            boCode: string;

            /** 激活 */
            activated: ibas.emYesNo;

            /** 自身权限 */
            self: ibas.emAuthoriseType;

            /** 部门-下级权限 */
            lowerLevel: ibas.emAuthoriseType;

            /** 部门-同级权限 */
            equalLevel: ibas.emAuthoriseType;

            /** 部门-上级权限 */
            higherLevel: ibas.emAuthoriseType;

            /** 团队权限 */
            teams: ibas.emAuthoriseType;

            /** 角色权限 */
            rules: ibas.emAuthoriseType;

            /** 角色编码 */
            ruleCodes: string;

            /** 其他情况权限 */
            others: ibas.emAuthoriseType;

            /** 对象编号 */
            objectKey: number;

            /** 对象类型 */
            objectCode: string;

            /** 创建日期 */
            createDate: Date;

            /** 创建时间 */
            createTime: number;

            /** 修改日期 */
            updateDate: Date;

            /** 修改时间 */
            updateTime: number;

            /** 实例号（版本） */
            logInst: number;

            /** 服务系列 */
            series: number;

            /** 数据源 */
            dataSource: string;

            /** 创建用户 */
            createUserSign: number;

            /** 修改用户 */
            updateUserSign: number;

            /** 创建动作标识 */
            createActionId: string;

            /** 更新动作标识 */
            updateActionId: string;
        }
    }
}


