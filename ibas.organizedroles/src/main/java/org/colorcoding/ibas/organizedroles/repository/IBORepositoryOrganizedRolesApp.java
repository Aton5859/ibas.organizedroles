package org.colorcoding.ibas.organizedroles.repository;

import org.colorcoding.ibas.bobas.common.ICriteria;
import org.colorcoding.ibas.bobas.common.IOperationResult;
import org.colorcoding.ibas.initialfantasy.repository.IBORepositoryInitialFantasyApp;
import org.colorcoding.ibas.organizedroles.bo.organizationalstructure.IOrganizationalStructure;
import org.colorcoding.ibas.organizedroles.bo.ownership.IOwnership;
import org.colorcoding.ibas.organizedroles.bo.role.IRole;

/**
 * OrganizedRoles仓库应用
 */
public interface IBORepositoryOrganizedRolesApp extends IBORepositoryInitialFantasyApp {

	// --------------------------------------------------------------------------------------------//
	/**
	 * 查询-组织-结构
	 * 
	 * @param criteria
	 *            查询
	 * @return 操作结果
	 */
	IOperationResult<IOrganizationalStructure> fetchOrganizationalStructure(ICriteria criteria);

	/**
	 * 保存-组织-结构
	 * 
	 * @param bo
	 *            对象实例
	 * @return 操作结果
	 */
	IOperationResult<IOrganizationalStructure> saveOrganizationalStructure(IOrganizationalStructure bo);

	// --------------------------------------------------------------------------------------------//
	/**
	 * 查询-数据权限
	 * 
	 * @param criteria
	 *            查询
	 * @return 操作结果
	 */
	IOperationResult<IOwnership> fetchOwnership(ICriteria criteria);

	/**
	 * 保存-数据权限
	 * 
	 * @param bo
	 *            对象实例
	 * @return 操作结果
	 */
	IOperationResult<IOwnership> saveOwnership(IOwnership bo);

	// --------------------------------------------------------------------------------------------//
	/**
	 * 查询-角色
	 * 
	 * @param criteria
	 *            查询
	 * @return 操作结果
	 */
	IOperationResult<IRole> fetchRole(ICriteria criteria);

	/**
	 * 保存-角色
	 * 
	 * @param bo
	 *            对象实例
	 * @return 操作结果
	 */
	IOperationResult<IRole> saveRole(IRole bo);

	// --------------------------------------------------------------------------------------------//

}
