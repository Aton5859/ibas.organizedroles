package org.colorcoding.ibas.organizedroles.service.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.colorcoding.ibas.bobas.common.*;
import org.colorcoding.ibas.organizedroles.repository.*;
import org.colorcoding.ibas.organizedroles.bo.organizationalstructure.*;
import org.colorcoding.ibas.organizedroles.bo.ownership.*;
import org.colorcoding.ibas.organizedroles.bo.role.*;

/**
* OrganizedRoles 数据服务JSON
*/
@Path("data")
public class DataService extends BORepositoryOrganizedRoles {

    //--------------------------------------------------------------------------------------------//
    /**
     * 查询-组织-结构
     * @param criteria 查询
     * @param token 口令
     * @return 操作结果
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("fetchOrganizationalStructure")
    public OperationResult<OrganizationalStructure> fetchOrganizationalStructure(Criteria criteria, @QueryParam("token") String token) {
        return super.fetchOrganizationalStructure(criteria, token);
    }

    /**
     * 保存-组织-结构
     * @param bo 对象实例
     * @param token 口令
     * @return 操作结果
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("saveOrganizationalStructure")
    public OperationResult<OrganizationalStructure> saveOrganizationalStructure(OrganizationalStructure bo, @QueryParam("token") String token) {
        return super.saveOrganizationalStructure(bo, token);
    }

    //--------------------------------------------------------------------------------------------//
    /**
     * 查询-数据权限
     * @param criteria 查询
     * @param token 口令
     * @return 操作结果
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("fetchOwnership")
    public OperationResult<Ownership> fetchOwnership(Criteria criteria, @QueryParam("token") String token) {
        return super.fetchOwnership(criteria, token);
    }

    /**
     * 保存-数据权限
     * @param bo 对象实例
     * @param token 口令
     * @return 操作结果
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("saveOwnership")
    public OperationResult<Ownership> saveOwnership(Ownership bo, @QueryParam("token") String token) {
        return super.saveOwnership(bo, token);
    }

    //--------------------------------------------------------------------------------------------//
    /**
     * 查询-角色
     * @param criteria 查询
     * @param token 口令
     * @return 操作结果
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("fetchRole")
    public OperationResult<Role> fetchRole(Criteria criteria, @QueryParam("token") String token) {
        return super.fetchRole(criteria, token);
    }

    /**
     * 保存-角色
     * @param bo 对象实例
     * @param token 口令
     * @return 操作结果
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("saveRole")
    public OperationResult<Role> saveRole(Role bo, @QueryParam("token") String token) {
        return super.saveRole(bo, token);
    }

    //--------------------------------------------------------------------------------------------//

}
