/**
 * @license
 * Copyright color-coding studio. All Rights Reserved.
 *
 * Use of this source code is governed by an Apache License, Version 2.0
 * that can be found in the LICENSE file at http://www.apache.org/licenses/LICENSE-2.0
 */

import * as ibas from "ibas/index";
import * as bo from "./bo/index";
import { IBORepositoryOrganizedRoles, BO_REPOSITORY_ORGANIZEDROLES } from "../api/index";
import { DataConverter4OR } from "./DataConverters";

/** 业务对象仓库 */
export class BORepositoryOrganizedRoles extends ibas.BORepositoryApplication implements IBORepositoryOrganizedRoles {

    /** 创建此模块的后端与前端数据的转换者 */
    protected createConverter(): ibas.IDataConverter {
        return new DataConverter4OR;
    }

    /**
     * 上传文件
     * @param caller 调用者
     */
    upload(caller: ibas.UploadFileCaller<ibas.FileData>): void {
        if (!this.address.endsWith("/")) { this.address += "/"; }
        let fileRepository: ibas.FileRepositoryUploadAjax = new ibas.FileRepositoryUploadAjax();
        fileRepository.address = this.address.replace("/services/rest/data/", "/services/rest/file/");
        fileRepository.token = this.token;
        fileRepository.converter = this.createConverter();
        fileRepository.upload("upload", caller);
    }
    /**
     * 下载文件
     * @param caller 调用者
     */
    download(caller: ibas.DownloadFileCaller<Blob>): void {
        if (!this.address.endsWith("/")) { this.address += "/"; }
        let fileRepository: ibas.FileRepositoryDownloadAjax = new ibas.FileRepositoryDownloadAjax();
        fileRepository.address = this.address.replace("/services/rest/data/", "/services/rest/file/");
        fileRepository.token = this.token;
        fileRepository.converter = this.createConverter();
        fileRepository.download("download", caller);
    }
    /**
     * 查询 组织-结构
     * @param fetcher 查询者
     */
    fetchOrganizationalStructure(fetcher: ibas.FetchCaller<bo.OrganizationalStructure>): void {
        super.fetch(bo.OrganizationalStructure.name, fetcher);
    }
    /**
     * 保存 组织-结构
     * @param saver 保存者
     */
    saveOrganizationalStructure(saver: ibas.SaveCaller<bo.OrganizationalStructure>): void {
        super.save(bo.OrganizationalStructure.name, saver);
    }

    /**
     * 查询 数据权限
     * @param fetcher 查询者
     */
    fetchOwnership(fetcher: ibas.FetchCaller<bo.Ownership>): void {
        super.fetch(bo.Ownership.name, fetcher);
    }
    /**
     * 保存 数据权限
     * @param saver 保存者
     */
    saveOwnership(saver: ibas.SaveCaller<bo.Ownership>): void {
        super.save(bo.Ownership.name, saver);
    }

    /**
     * 查询 角色
     * @param fetcher 查询者
     */
    fetchRole(fetcher: ibas.FetchCaller<bo.Role>): void {
        super.fetch(bo.Role.name, fetcher);
    }
    /**
     * 保存 角色
     * @param saver 保存者
     */
    saveRole(saver: ibas.SaveCaller<bo.Role>): void {
        super.save(bo.Role.name, saver);
    }

}
// 注册业务对象仓库到工厂
ibas.boFactory.register(BO_REPOSITORY_ORGANIZEDROLES, BORepositoryOrganizedRoles);
