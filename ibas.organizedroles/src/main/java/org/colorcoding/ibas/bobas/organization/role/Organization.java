package org.colorcoding.ibas.bobas.organization.role;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "Organization")
@XmlRootElement(name = "Organization")
public class Organization {
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Organization) {
			Organization org = (Organization) obj;
			if (this.getCode().equals(org.getCode())) {
				return true;
			}
		}
		return super.equals(obj);
	}

	@XmlElement(name = "code")
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@XmlElementWrapper(name = "organizations")
	@XmlElement(name = "organization", type = Organization.class)
	private List<Organization> organizations;

	public List<Organization> getOrganizations() {
		if (organizations == null) {
			organizations = new ArrayList<>();
		}
		return organizations;
	}

	public void setOrganizations(List<Organization> organizations) {
		this.organizations = organizations;
	}

	@XmlElement(name = "manager")
	private OrgUser manager;

	public OrgUser getManager() {
		return manager;
	}

	public void setManager(OrgUser manager) {
		this.manager = manager;
	}

	@XmlElementWrapper(name = "members")
	@XmlElement(name = "members", type = OrgUser.class)
	private List<OrgUser> members;

	public List<OrgUser> getMembers() {
		if (members == null) {
			members = new ArrayList<>();
		}
		return members;
	}

	public void setMembers(List<OrgUser> members) {
		this.members = members;
	}

	@Override
	public String toString() {
		return String.format("{Org [%s] manager [%s]}", this.getCode(), this.getManager());
	}
}
