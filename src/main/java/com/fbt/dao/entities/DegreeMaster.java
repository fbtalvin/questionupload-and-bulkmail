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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * DegreeMaster generated by hbm2java
 */
@Entity
@Table(name = "degree_master", catalog = "talentex_sme")
public class DegreeMaster implements java.io.Serializable {

	private Integer id;
	private String degree;
	private String department;
	private String graduation;
	private Set<BetaValueMaster> betaValueMasters = new HashSet<BetaValueMaster>();

	public DegreeMaster() {
	}

	public DegreeMaster(String degree, String department, String graduation,
			Set<BetaValueMaster> betaValueMasters) {
		this.degree = degree;
		this.department = department;
		this.graduation = graduation;
		this.betaValueMasters = betaValueMasters;
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

	@Column(name = "degree")
	public String getDegree() {
		return this.degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	@Column(name = "department")
	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Column(name = "graduation")
	public String getGraduation() {
		return this.graduation;
	}

	public void setGraduation(String graduation) {
		this.graduation = graduation;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "degreeMaster")
	public Set<BetaValueMaster> getBetaValueMasters() {
		return this.betaValueMasters;
	}

	public void setBetaValueMasters(Set<BetaValueMaster> betaValueMasters) {
		this.betaValueMasters = betaValueMasters;
	}

}
