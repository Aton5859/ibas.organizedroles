package org.colorcoding.ibas.organizedroles.service.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.colorcoding.ibas.bobas.common.*;
import org.colorcoding.ibas.bobas.cxf.WebServicePath;
import org.colorcoding.ibas.organizedroles.repository.*;
import org.colorcoding.ibas.organizedroles.bo.organizationalstructure.*;
import org.colorcoding.ibas.organizedroles.bo.ownership.*;
import org.colorcoding.ibas.organizedroles.bo.role.*;

/**
* OrganizedRoles 数据服务JSON
*/
@WebService
@WebServicePath("data")
public class DataService extends BORepositoryOrganizedRoles {

    //--------------------------------------------------------------------------------------------//
    /**
     * 查询-组织-结构
     * @param criteria 查询
     * @param token 口令
     * @return 操作结果
     */
    @WebMethod
    public OperationResult<OrganizationalStructure> fetchOrganizationalStructure(@WebParam(name = "criteria") Criteria criteria, @WebParam(name = "token") String token) {
        return super.fetchOrganizationalStructure(criteria, token);
    }

    /**
     * 保存-组织-结构
     * @param bo 对象实例
     * @param token 口令
     * @return 操作结果
     */
    @WebMethod
    public OperationResult<OrganizationalStructure> saveOrganizationalStructure(@WebParam(name = "bo") OrganizationalStructure bo, @WebParam(name = "token") String token) {
        return super.saveOrganizationalStructure(bo, token);
    }

    //--------------------------------------------------------------------------------------------//
    /**
     * 查询-数据权限
     * @param criteria 查询
     * @param token 口令
     * @return 操作结果
     */
    @WebMethod
    public OperationResult<Ownership> fetchOwnership(@WebParam(name = "criteria") Criteria criteria, @WebParam(name = "token") String token) {
        return super.fetchOwnership(criteria, token);
    }

    /**
     * 保存-数据权限
     * @param bo 对象实例
     * @param token 口令
     * @return 操作结果
     */
    @WebMethod
    public OperationResult<Ownership> saveOwnership(@WebParam(name = "bo") Ownership bo, @WebParam(name = "token") String token) {
        return super.saveOwnership(bo, token);
    }

    //--------------------------------------------------------------------------------------------//
    /**
     * 查询-角色
     * @param criteria 查询
     * @param token 口令
     * @return 操作结果
     */
    @WebMethod
    public OperationResult<Role> fetchRole(@WebParam(name = "criteria") Criteria criteria, @WebParam(name = "token") String token) {
        return super.fetchRole(criteria, token);
    }

    /**
     * 保存-角色
     * @param bo 对象实例
     * @param token 口令
     * @return 操作结果
     */
    @WebMethod
    public OperationResult<Role> saveRole(@WebParam(name = "bo") Role bo, @WebParam(name = "token") String token) {
        return super.saveRole(bo, token);
    }

    //--------------------------------------------------------------------------------------------//

}
