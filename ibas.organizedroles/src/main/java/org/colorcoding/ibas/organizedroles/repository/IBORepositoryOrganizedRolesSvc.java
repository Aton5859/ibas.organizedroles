package org.colorcoding.ibas.organizedroles.repository;

import org.colorcoding.ibas.bobas.common.*;
import org.colorcoding.ibas.bobas.repository.*;
import org.colorcoding.ibas.organizedroles.bo.organizationalstructure.*;
import org.colorcoding.ibas.organizedroles.bo.ownership.*;
import org.colorcoding.ibas.organizedroles.bo.role.*;

/**
* OrganizedRoles仓库服务
*/
public interface IBORepositoryOrganizedRolesSvc extends IBORepositorySmartService {


    //--------------------------------------------------------------------------------------------//
    /**
     * 查询-组织-结构
     * @param criteria 查询
     * @param token 口令
     * @return 操作结果
     */
    OperationResult<OrganizationalStructure> fetchOrganizationalStructure(ICriteria criteria, String token);

    /**
     * 保存-组织-结构
     * @param bo 对象实例
     * @param token 口令
     * @return 操作结果
     */
    OperationResult<OrganizationalStructure> saveOrganizationalStructure(OrganizationalStructure bo, String token);

    //--------------------------------------------------------------------------------------------//
    /**
     * 查询-数据权限
     * @param criteria 查询
     * @param token 口令
     * @return 操作结果
     */
    OperationResult<Ownership> fetchOwnership(ICriteria criteria, String token);

    /**
     * 保存-数据权限
     * @param bo 对象实例
     * @param token 口令
     * @return 操作结果
     */
    OperationResult<Ownership> saveOwnership(Ownership bo, String token);

    //--------------------------------------------------------------------------------------------//
    /**
     * 查询-角色
     * @param criteria 查询
     * @param token 口令
     * @return 操作结果
     */
    OperationResult<Role> fetchRole(ICriteria criteria, String token);

    /**
     * 保存-角色
     * @param bo 对象实例
     * @param token 口令
     * @return 操作结果
     */
    OperationResult<Role> saveRole(Role bo, String token);

    //--------------------------------------------------------------------------------------------//

}
