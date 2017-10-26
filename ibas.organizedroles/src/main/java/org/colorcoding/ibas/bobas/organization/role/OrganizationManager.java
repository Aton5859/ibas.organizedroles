package org.colorcoding.ibas.bobas.organization.role;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.colorcoding.ibas.bobas.common.ConditionOperation;
import org.colorcoding.ibas.bobas.common.Criteria;
import org.colorcoding.ibas.bobas.common.ICondition;
import org.colorcoding.ibas.bobas.common.ICriteria;
import org.colorcoding.ibas.bobas.common.IOperationResult;
import org.colorcoding.ibas.bobas.core.Daemon;
import org.colorcoding.ibas.bobas.core.ISingleDaemonTask;
import org.colorcoding.ibas.bobas.core.InvalidDaemonTaskException;
import org.colorcoding.ibas.bobas.data.DateTime;
import org.colorcoding.ibas.bobas.data.emYesNo;
import org.colorcoding.ibas.bobas.messages.Logger;
import org.colorcoding.ibas.bobas.organization.IOrganizationManager;
import org.colorcoding.ibas.bobas.organization.IUser;
import org.colorcoding.ibas.bobas.organization.OrganizationFactory;
import org.colorcoding.ibas.bobas.util.EncryptMD5;
import org.colorcoding.ibas.organizedroles.MyConfiguration;
import org.colorcoding.ibas.organizedroles.bo.organizationalstructure.IOrganizationalRole;
import org.colorcoding.ibas.organizedroles.bo.organizationalstructure.IOrganizationalStructure;
import org.colorcoding.ibas.organizedroles.bo.organizationalstructure.IRoleMember;
import org.colorcoding.ibas.organizedroles.bo.organizationalstructure.OrganizationalStructure;
import org.colorcoding.ibas.organizedroles.repository.BORepositoryOrganizedRoles;
import org.colorcoding.ibas.organizedroles.repository.IBORepositoryOrganizedRolesApp;

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "organizations")
@XmlRootElement(name = "organizations")
public class OrganizationManager implements IOrganizationManager {

	public static String createToken(org.colorcoding.ibas.initialfantasy.bo.organization.IUser user) {
		return createToken(user.getPassword(), user.getDocEntry());
	}

	public static String createToken(OrgUser user) {
		return createToken(user.getPassword(), user.getId());
	}

	private static String createToken(String password, int id) {
		return EncryptMD5.md5(password + String.valueOf(id));
	}

	public OrganizationManager() {
		// 设置组织结构刷新时间，默认300秒
		this.setFreshTime(MyConfiguration.getConfigValue(MyConfiguration.CONFIG_ITEM_ORGANIZATION_REFRESH_TIME, 300));
	}

	private ArrayList<OrgUser> undistributedUser;

	private ArrayList<OrgUser> getUndistributedUsers() {
		if (undistributedUser == null) {
			undistributedUser = new ArrayList<>();
		}
		return this.undistributedUser;
	}

	@Override
	public void register(IUser user) {
		for (int i = 0; i < this.getUndistributedUsers().size(); i++) {
			IUser item = this.getUndistributedUsers().get(i);
			if (item.getId() == user.getId()) {
				this.getUndistributedUsers().set(i, (OrgUser) user);
				return;
			}
		}
		this.getUndistributedUsers().add((OrgUser) user);
	}

	@Override
	public IUser getUser(String token) {
		// 系统用户
		if (OrganizationFactory.SYSTEM_USER.getToken().equals(token)) {
			return OrganizationFactory.SYSTEM_USER;
		}
		// 已分配组织的操作用户
		for (OrgUser user : this.getUsers()) {
			if (user.getToken().equals(token)) {
				return user;
			}
		}
		// 未分配组织的操作用户
		for (OrgUser user : this.getUndistributedUsers()) {
			if (user.getToken().equals(token)) {
				return user;
			}
		}
		return null;
	}

	@Override
	public IUser getUser(int id) {
		// 系统用户
		if (OrganizationFactory.SYSTEM_USER.getId() == id) {
			return OrganizationFactory.SYSTEM_USER;
		}
		// 已分配组织的操作用户
		for (IUser user : this.getUsers()) {
			if (user.getId() == id) {
				return user;
			}
		}
		// 未分配组织的用户
		for (IUser user : this.getUndistributedUsers()) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}

	@Override
	public String[] getRoles(IUser user) {
		List<String> roles = new ArrayList<>();
		for (OrgUser item : this.getUsers()) {
			if (item.getId() == user.getId()) {
				if (roles.contains(item.getRole())) {
					continue;
				}
				roles.add(item.getRole());
			}
		}
		return roles.toArray(new String[] {});
	}

	private List<OrgUser> getUsers(Organization organization) {
		ArrayList<OrgUser> users = new ArrayList<>();
		for (Organization org : organization.getOrganizations()) {
			users.addAll(this.getUsers(org));
		}
		users.addAll(organization.getMembers());
		users.add(organization.getManager());
		return users;
	}

	public OrgUser[] getUsers() {
		ArrayList<OrgUser> users = new ArrayList<>();
		for (Organization org : this.getOrganizations(false)) {
			users.addAll(this.getUsers(org));
		}
		return users.toArray(new OrgUser[] {});
	}

	@XmlElement(name = "organization", type = Organization.class)
	private Organization[] organizations;

	/**
	 * 获取全部组织
	 * 
	 * @param flat
	 *            true，扁平的；false，仅根组织
	 * @return
	 */
	public Organization[] getOrganizations(boolean flat) {
		if (this.organizations == null) {
			return new Organization[] {};
		}
		if (!flat) {
			return this.organizations;
		}
		ArrayList<Organization> orgs = new ArrayList<>();
		for (Organization org : this.organizations) {
			orgs.addAll(this.getOrganizations(org));
		}
		return orgs.toArray(new Organization[] {});
	}

	private List<Organization> getOrganizations(Organization organization) {
		ArrayList<Organization> orgs = new ArrayList<>();
		for (Organization org : organization.getOrganizations()) {
			orgs.addAll(this.getOrganizations(org));
		}
		orgs.add(organization);
		return orgs;
	}

	/**
	 * 获取用户所属的组织
	 * 
	 * @param user
	 *            用户
	 * @return
	 */
	public Organization[] getOrganizations(IUser user) {
		ArrayList<Organization> orgs = new ArrayList<>();
		for (Organization org : this.getOrganizations(true)) {
			if (org.getManager().equals(user)) {
				orgs.add(org);
			}
			for (OrgUser item : org.getMembers()) {
				if (item.equals(user)) {
					orgs.add(org);
					break;
				}
			}
		}
		return orgs.toArray(new Organization[] {});
	}

	public void initialize() {
		this.initialize(false);
	}

	public synchronized void initialize(boolean force) {
		this.undistributedUser = null;
		this.organizations = null;
		this.repository = null;
		this.load(force);
		if (force) {
			// 强制初始化，不进行缓存相关处理
			return;
		}
		// 注册组织刷新任务
		try {
			Daemon.register(new ISingleDaemonTask() {

				@Override
				public void run() {
					if (organizations_time_stamp == 0) {
						// 未加载不做处理
						return;
					}
					load(false);// 重新加载组织
				}

				@Override
				public String getName() {
					return "organizations refresh";
				}

				@Override
				public long getInterval() {
					return getFreshTime();
				}

				@Override
				public boolean isActivated() {
					return true;
				}

				@Override
				public long getKeepTime() {
					return 120;
				}

				private volatile String lockSignature;

				@Override
				public String getLockSignature() {
					if (this.lockSignature == null) {
						synchronized (this) {
							if (this.lockSignature == null) {
								// 当前数据库地址和数据库名称作为锁标记
								String dbServer = MyConfiguration
										.getConfigValue("Master" + MyConfiguration.CONFIG_ITEM_DB_SERVER);
								String dbName = MyConfiguration
										.getConfigValue("Master" + MyConfiguration.CONFIG_ITEM_DB_NAME);
								String sign = EncryptMD5.md5("org_fresh_", dbServer, dbName);
								this.lockSignature = sign;
							}
						}
					}
					return this.lockSignature;
				}

			});
		} catch (InvalidDaemonTaskException e) {
			Logger.log(e);
		}
	}

	protected String getCacheFilePath() {
		// 当前数据库地址和数据库名称作为锁标记
		String dbServer = MyConfiguration.getConfigValue("Master" + MyConfiguration.CONFIG_ITEM_DB_SERVER);
		String dbName = MyConfiguration.getConfigValue("Master" + MyConfiguration.CONFIG_ITEM_DB_NAME);
		String sign = EncryptMD5.md5(dbServer, dbName);
		return MyConfiguration.getTempFolder() + File.separator + "~org_cache_" + sign + ".tmp";
	}

	private long freshTime;

	public long getFreshTime() {
		if (this.freshTime < 60) {
			this.freshTime = 60;
		}
		return this.freshTime;
	}

	public void setFreshTime(long value) {
		this.freshTime = value;
	}

	private long organizations_time_stamp;// 组织的时间戳

	protected void load(boolean force) {
		// 尝试从缓存中加载数据
		JAXBContext context = null;
		File file = new File(this.getCacheFilePath());
		if (file.isFile() && file.exists() && !force) {
			Long fileTime = file.lastModified();
			Long nowTime = System.currentTimeMillis();
			if ((nowTime - fileTime) <= (this.getFreshTime() * 1000)) {
				// 缓存文件有效期内
				try {
					context = JAXBContext.newInstance(OrganizationManager.class);
					Unmarshaller unmarshaller = context.createUnmarshaller();
					FileInputStream fileStream = new FileInputStream(file);
					OrganizationManager manager = (OrganizationManager) unmarshaller.unmarshal(fileStream);
					this.organizations = manager.organizations;
					this.organizations_time_stamp = System.currentTimeMillis();
					// 有效的数据
					Logger.log(String.format("organization: read the cached data, [%s].", file.getPath()));
					return;// 退出
				} catch (JAXBException | IOException e) {
					Logger.log(e);
				}
			}
		}
		List<Organization> organizations = this.load(-1);// 加载根
		this.organizations = organizations.toArray(new Organization[] {});
		this.undistributedUser = new ArrayList<>();// 初始化未分配组织的用户
		this.organizations_time_stamp = System.currentTimeMillis();
		Logger.log(String.format("organization: read data in the database."));
		try {
			if (!file.exists()) {
				file.getParentFile().mkdirs();
				file.createNewFile();
			} else {
				file.delete();
				file.createNewFile();
			}
			context = JAXBContext.newInstance(OrganizationManager.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			FileOutputStream out = new FileOutputStream(file, false);
			marshaller.marshal(this, out);
			out.flush();
			out.close();
			Logger.log(String.format("organization: cache data in file, [%s].", file.getPath()));
		} catch (JAXBException | IOException e) {
			Logger.log(e);
		}
	}

	private BORepositoryOrganizedRoles repository;

	private IBORepositoryOrganizedRolesApp createRepository() {
		if (repository == null) {
			try {
				repository = new BORepositoryOrganizedRoles();
				repository.setUserToken(OrganizationFactory.SYSTEM_USER.getToken());
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return repository;
	}

	protected List<Organization> load(Integer node) {
		List<Organization> results = new ArrayList<>();
		try {
			ICriteria criteria = new Criteria();
			ICondition condition = criteria.getConditions().create();
			condition.setAlias(OrganizationalStructure.PROPERTY_BELONGING.getName());
			condition.setValue(node);
			condition = criteria.getConditions().create();
			condition.setAlias(OrganizationalStructure.PROPERTY_VALIDDATE.getName());
			condition.setOperation(ConditionOperation.LESS_EQUAL);
			condition.setValue(DateTime.getToday());
			condition = criteria.getConditions().create();
			condition.setAlias(OrganizationalStructure.PROPERTY_INVALIDDATE.getName());
			condition.setOperation(ConditionOperation.GRATER_EQUAL);
			condition.setValue(DateTime.getToday());
			IBORepositoryOrganizedRolesApp boRepository = this.createRepository();
			IOperationResult<IOrganizationalStructure> operationResult = boRepository
					.fetchOrganizationalStructure(criteria);
			if (operationResult.getError() != null) {
				throw operationResult.getError();
			}
			if (operationResult.getResultCode() != 0) {
				throw new Exception(operationResult.getMessage());
			}
			for (IOrganizationalStructure orgItem : operationResult.getResultObjects()) {
				Organization organization = new Organization();
				organization.setCode(orgItem.getOrganization());
				organization.setManager(this.createUser(orgItem.getManager()));
				if (organization.getManager() != null) {
					organization.getManager().setRole(String.format("MANAGER@%s", organization.getCode()));
				}
				for (IOrganizationalRole posItem : orgItem.getOrganizationalRoles()) {
					for (IRoleMember empItem : posItem.getRoleMembers()) {
						OrgUser user = this.createUser(empItem.getMember());
						if (user != null) {
							user.setRole(posItem.getRole());
							organization.getMembers().add(user);
						}
					}
				}
				// 加载子项
				organization.setOrganizations(this.load(orgItem.getObjectKey()));
				results.add(organization);
			}
		} catch (Exception e) {
			Logger.log(e);
		}
		return results;
	}

	private OrgUser createUser(String key) {
		if (key == null || key.isEmpty()) {
			return null;
		}
		ICriteria criteria = new Criteria();
		ICondition condition = criteria.getConditions().create();
		condition.setAlias(org.colorcoding.ibas.initialfantasy.bo.organization.User.PROPERTY_CODE.getName());
		condition.setValue(key);
		condition = criteria.getConditions().create();
		condition.setAlias(org.colorcoding.ibas.initialfantasy.bo.organization.User.PROPERTY_ACTIVATED.getName());
		condition.setValue(emYesNo.YES);
		IBORepositoryOrganizedRolesApp boRepository = this.createRepository();
		IOperationResult<org.colorcoding.ibas.initialfantasy.bo.organization.IUser> operationResult = boRepository
				.fetchUser(criteria);
		if (operationResult.getError() == null && operationResult.getResultCode() == 0) {
			org.colorcoding.ibas.initialfantasy.bo.organization.IUser tmpUser = operationResult.getResultObjects()
					.firstOrDefault();
			if (tmpUser != null) {
				OrgUser user = OrgUser.create(tmpUser);
				user.setToken(createToken(tmpUser));
				return user;
			}
		}
		return null;
	}

	/**
	 * 判断两者组织关系
	 * 
	 * someone是user的什么
	 * 
	 * @param user
	 * @param other
	 * @return
	 * 
	 * 		所属组织的manager即为领导，若本身为manager，则上级组织的manager为领导。所属同一组织且同角色即为同事， 领导没有同事
	 *         作为manager所属组织中的成员即为下属，而自身为上级组织manager的下属。
	 */
	public OrganizationalRelationship getRelationship(IUser user, IUser someone) {
		if (user == null || someone == null) {
			return OrganizationalRelationship.NONE;
		}
		if (user.equals(OrganizationFactory.UNKNOWN_USER) || someone.equals(OrganizationFactory.UNKNOWN_USER)) {
			return OrganizationalRelationship.NONE;
		}
		// 获取组织
		Organization[] userOrg = this.getOrganizations(user);
		if (userOrg == null || userOrg.length == 0) {
			return OrganizationalRelationship.NONE;
		}
		Organization[] someOrg = this.getOrganizations(someone);
		if (someOrg == null || someOrg.length == 0) {
			return OrganizationalRelationship.NONE;
		}
		// 所属组织的manager即为领导，若本身为manager，则上级组织的manager为领导
		for (Organization organization : someOrg) {
			// someone是领导
			if (organization.getManager().equals(someone)) {
				for (OrgUser item : organization.getMembers()) {
					if (item.equals(user)) {
						return OrganizationalRelationship.LEADER;
					}
				}
				// 上级组织的manager为领导
				if (this.isSubordinateManagers(organization, user)) {
					return OrganizationalRelationship.LEADER;
				}
			}
		}
		// 所属同一组织即为同事，领导没有同事
		for (Organization organization : someOrg) {
			for (OrgUser item : organization.getMembers()) {
				if (item instanceof OrgUser && user instanceof OrgUser) {
					if (item.equals(user) && item.getRole().equals(((OrgUser) user).getRole())) {
						return OrganizationalRelationship.COLLEAGUES;
					}
				} else {
					if (item.equals(user)) {
						return OrganizationalRelationship.COLLEAGUES;
					}
				}
			}
		}
		// 作为manager所属组织中的成员即为下属，而自身为上级组织manager的下属
		for (Organization organization : userOrg) {
			// user是领导
			if (organization.getManager().equals(user)) {
				for (OrgUser item : organization.getMembers()) {
					if (item.equals(someone)) {
						return OrganizationalRelationship.SUBORDINATE;
					}
				}
				// 上级组织manager的下属
				if (this.isSubordinateManagers(organization, someone)) {
					return OrganizationalRelationship.SUBORDINATE;
				}
			}
		}
		// 没有解析出来的均为无关系
		return OrganizationalRelationship.NONE;
	}

	/**
	 * 判断用户是否为下级组织manger
	 * 
	 * @param organization
	 *            组织
	 * @param user
	 *            用户
	 * @return
	 */
	private boolean isSubordinateManagers(Organization organization, IUser user) {
		if (organization.getManager().equals(user)) {
			return true;
		}
		for (Organization item : organization.getOrganizations()) {
			boolean status = this.isSubordinateManagers(item, user);
			if (status) {
				return true;
			}
		}
		return false;
	}

}
