package org.colorcoding.ibas.organizedroles.test.bo;

import org.colorcoding.ibas.bobas.common.ICriteria;
import org.colorcoding.ibas.bobas.common.IOperationResult;
import org.colorcoding.ibas.organizedroles.bo.organizationalstructure.IOrganizationalRole;
import org.colorcoding.ibas.organizedroles.bo.organizationalstructure.OrganizationalStructure;
import org.colorcoding.ibas.organizedroles.repository.BORepositoryOrganizedRoles;
import org.colorcoding.ibas.organizedroles.repository.IBORepositoryOrganizedRolesApp;

import junit.framework.TestCase;

/**
 * 组织-结构 测试
 * 
 */
public class testOrganizationalStructure extends TestCase {
	/**
	 * 获取连接口令
	 */
	String getToken() {
		return "";
	}

	/**
	 * 基本项目测试
	 * 
	 * @throws Exception
	 */
	public void testBasicItems() throws Exception {
		OrganizationalStructure bo = new OrganizationalStructure();
		// 测试属性赋值

		// 测试组织-角色
		IOrganizationalRole organizationalrole = bo.getOrganizationalRoles().create();
		System.out.println(String.format("new item: %s", organizationalrole.toString()));
		// 测试属性赋值

		// 测试对象的保存和查询
		IOperationResult<?> operationResult = null;
		ICriteria criteria = null;
		IBORepositoryOrganizedRolesApp boRepository = new BORepositoryOrganizedRoles();
		// 设置用户口令
		boRepository.setUserToken(this.getToken());

		// 测试保存
		operationResult = boRepository.saveOrganizationalStructure(bo);
		assertEquals(operationResult.getMessage(), operationResult.getResultCode(), 0);
		OrganizationalStructure boSaved = (OrganizationalStructure) operationResult.getResultObjects().firstOrDefault();

		// 测试查询
		criteria = boSaved.getCriteria();
		criteria.setResultCount(10);
		operationResult = boRepository.fetchOrganizationalStructure(criteria);
		assertEquals(operationResult.getMessage(), operationResult.getResultCode(), 0);

	}

}
