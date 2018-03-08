/**
 * @license
 * Copyright Color-Coding Studio. All Rights Reserved.
 *
 * Use of this source code is governed by an Apache License, Version 2.0
 * that can be found in the LICENSE file at http://www.apache.org/licenses/LICENSE-2.0
 */
namespace organizedroles {
    export namespace bo {

        /** 业务仓库 */
        export interface IBORepositoryOrganizedRoles extends ibas.IBORepositoryApplication {

            /**
             * 上传文件
             * @param caller 调用者
             */
            upload(caller: ibas.IUploadFileCaller<ibas.FileData>): void;
            /**
             * 下载文件
             * @param caller 调用者
             */
            download(caller: ibas.IDownloadFileCaller<Blob>): void;
            /**
             * 查询 组织-结构
             * @param fetcher 查询者
             */
            fetchOrganizationalStructure(fetcher: ibas.IFetchCaller<bo.IOrganizationalStructure>): void;
            /**
             * 保存 组织-结构
             * @param saver 保存者
             */
            saveOrganizationalStructure(saver: ibas.ISaveCaller<bo.IOrganizationalStructure>): void;

            /**
             * 查询 数据权限
             * @param fetcher 查询者
             */
            fetchOwnership(fetcher: ibas.IFetchCaller<bo.IOwnership>): void;
            /**
             * 保存 数据权限
             * @param saver 保存者
             */
            saveOwnership(saver: ibas.ISaveCaller<bo.IOwnership>): void;

            /**
             * 查询 角色
             * @param fetcher 查询者
             */
            fetchRole(fetcher: ibas.IFetchCaller<bo.IRole>): void;
            /**
             * 保存 角色
             * @param saver 保存者
             */
            saveRole(saver: ibas.ISaveCaller<bo.IRole>): void;
        }
    }

}
