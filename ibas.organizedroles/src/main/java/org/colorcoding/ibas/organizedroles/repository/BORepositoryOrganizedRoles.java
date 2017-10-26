package org.colorcoding.ibas.organizedroles.repository;

import org.colorcoding.ibas.bobas.common.ICriteria;
import org.colorcoding.ibas.bobas.common.IOperationResult;
import org.colorcoding.ibas.bobas.common.OperationResult;
import org.colorcoding.ibas.initialfantasy.repository.BORepositoryInitialFantasy;
import org.colorcoding.ibas.organizedroles.bo.organizationalstructure.IOrganizationalStructure;
import org.colorcoding.ibas.organizedroles.bo.organizationalstructure.OrganizationalStructure;
import org.colorcoding.ibas.organizedroles.bo.ownership.IOwnership;
import org.colorcoding.ibas.organizedroles.bo.ownership.Ownership;
import org.colorcoding.ibas.organizedroles.bo.role.IRole;
import org.colorcoding.ibas.organizedroles.bo.role.Role;

/**
 * OrganizedRoles仓库
 */
public class BORepositoryOrganizedRoles extends BORepositoryInitialFantasy
		implements IBORepositoryOrganizedRolesSvc, IBORepositoryOrganizedRolesApp {

	// --------------------------------------------------------------------------------------------//
	/**
	 * 查询-组织-结构
	 * 
	 * @param criteria
	 *            查询
	 * @param token
	 *            口令
	 * @return 操作结果
	 */
	public OperationResult<OrganizationalStructure> fetchOrganizationalStructure(ICriteria criteria, String token) {
		return super.fetch(criteria, token, OrganizationalStructure.class);
	}

	/**
	 * 查询-组织-结构（提前设置用户口令）
	 * 
	 * @param criteria
	 *            查询
	 * @return 操作结果
	 */
	public IOperationResult<IOrganizationalStructure> fetchOrganizationalStructure(ICriteria criteria) {
		return new OperationResult<IOrganizationalStructure>(
				this.fetchOrganizationalStructure(criteria, this.getUserToken()));
	}

	/**
	 * 保存-组织-结构
	 * 
	 * @param bo
	 *            对象实例
	 * @param token
	 *            口令
	 * @return 操作结果
	 */
	public OperationResult<OrganizationalStructure> saveOrganizationalStructure(OrganizationalStructure bo,
			String token) {
		return super.save(bo, token);
	}

	/**
	 * 保存-组织-结构（提前设置用户口令）
	 * 
	 * @param bo
	 *            对象实例
	 * @return 操作结果
	 */
	public IOperationResult<IOrganizationalStructure> saveOrganizationalStructure(IOrganizationalStructure bo) {
		return new OperationResult<IOrganizationalStructure>(
				this.saveOrganizationalStructure((OrganizationalStructure) bo, this.getUserToken()));
	}

	// --------------------------------------------------------------------------------------------//
	/**
	 * 查询-数据权限
	 * 
	 * @param criteria
	 *            查询
	 * @param token
	 *            口令
	 * @return 操作结果
	 */
	public OperationResult<Ownership> fetchOwnership(ICriteria criteria, String token) {
		return super.fetch(criteria, token, Ownership.class);
	}

	/**
	 * 查询-数据权限（提前设置用户口令）
	 * 
	 * @param criteria
	 *            查询
	 * @return 操作结果
	 */
	public IOperationResult<IOwnership> fetchOwnership(ICriteria criteria) {
		return new OperationResult<IOwnership>(this.fetchOwnership(criteria, this.getUserToken()));
	}

	/**
	 * 保存-数据权限
	 * 
	 * @param bo
	 *            对象实例
	 * @param token
	 *            口令
	 * @return 操作结果
	 */
	public OperationResult<Ownership> saveOwnership(Ownership bo, String token) {
		return super.save(bo, token);
	}

	/**
	 * 保存-数据权限（提前设置用户口令）
	 * 
	 * @param bo
	 *            对象实例
	 * @return 操作结果
	 */
	public IOperationResult<IOwnership> saveOwnership(IOwnership bo) {
		return new OperationResult<IOwnership>(this.saveOwnership((Ownership) bo, this.getUserToken()));
	}

	// --------------------------------------------------------------------------------------------//
	/**
	 * 查询-角色
	 * 
	 * @param criteria
	 *            查询
	 * @param token
	 *            口令
	 * @return 操作结果
	 */
	public OperationResult<Role> fetchRole(ICriteria criteria, String token) {
		return super.fetch(criteria, token, Role.class);
	}

	/**
	 * 查询-角色（提前设置用户口令）
	 * 
	 * @param criteria
	 *            查询
	 * @return 操作结果
	 */
	public IOperationResult<IRole> fetchRole(ICriteria criteria) {
		return new OperationResult<IRole>(this.fetchRole(criteria, this.getUserToken()));
	}

	/**
	 * 保存-角色
	 * 
	 * @param bo
	 *            对象实例
	 * @param token
	 *            口令
	 * @return 操作结果
	 */
	public OperationResult<Role> saveRole(Role bo, String token) {
		return super.save(bo, token);
	}

	/**
	 * 保存-角色（提前设置用户口令）
	 * 
	 * @param bo
	 *            对象实例
	 * @return 操作结果
	 */
	public IOperationResult<IRole> saveRole(IRole bo) {
		return new OperationResult<IRole>(this.saveRole((Role) bo, this.getUserToken()));
	}

	// --------------------------------------------------------------------------------------------//

}
