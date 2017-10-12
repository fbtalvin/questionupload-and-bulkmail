package com.fbt.dao.entities;

// Generated Mar 31, 2015 1:18:31 PM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * JobRoleMaster generated by hbm2java
 */
@Entity
@Table(name = "job_role_master", catalog = "talentex_sme")
public class JobRoleMaster implements java.io.Serializable {

	private Integer id;
	private IndustryMaster industryMaster;
	private String jobRoleName;
	private Set<JobRoleTechMaster> jobRoleTechMasters = new HashSet<JobRoleTechMaster>();

	public JobRoleMaster() {
	}

	public JobRoleMaster(IndustryMaster industryMaster, String jobRoleName) {
		this.industryMaster = industryMaster;
		this.jobRoleName = jobRoleName;
	}

	public JobRoleMaster(IndustryMaster industryMaster, String jobRoleName,
			Set<JobRoleTechMaster> jobRoleTechMasters) {
		this.industryMaster = industryMaster;
		this.jobRoleName = jobRoleName;
		this.jobRoleTechMasters = jobRoleTechMasters;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "industry_master_id", nullable = false)
	public IndustryMaster getIndustryMaster() {
		return this.industryMaster;
	}

	public void setIndustryMaster(IndustryMaster industryMaster) {
		this.industryMaster = industryMaster;
	}

	@Column(name = "job_role_name", nullable = false)
	public String getJobRoleName() {
		return this.jobRoleName;
	}

	public void setJobRoleName(String jobRoleName) {
		this.jobRoleName = jobRoleName;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "jobRoleMaster")
	public Set<JobRoleTechMaster> getJobRoleTechMasters() {
		return this.jobRoleTechMasters;
	}

	public void setJobRoleTechMasters(Set<JobRoleTechMaster> jobRoleTechMasters) {
		this.jobRoleTechMasters = jobRoleTechMasters;
	}

}