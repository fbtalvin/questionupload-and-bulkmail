package com.fbt.dao.entities;

// Generated Jun 16, 2015 11:26:30 AM by Hibernate Tools 3.4.0.CR1

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
 * IndustrySkill generated by hbm2java
 */
@Entity
@Table(name = "industry_skill", catalog = "talentex_mocktest")
public class IndustrySkill implements java.io.Serializable {

	private Integer id;
	private String skills;
	private String skillType;
	private Set<Question> questions = new HashSet<Question>();

	public IndustrySkill() {
	}

	public IndustrySkill(String skills, String skillType) {
		this.skills = skills;
		this.skillType = skillType;
	}

	public IndustrySkill(String skills, String skillType, Set<Question> questions) {
		this.skills = skills;
		this.skillType = skillType;
		this.questions = questions;
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

	@Column(name = "skills", nullable = false, length = 100)
	public String getSkills() {
		return this.skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	@Column(name = "skill_type", nullable = false, length = 50)
	public String getSkillType() {
		return this.skillType;
	}

	public void setSkillType(String skillType) {
		this.skillType = skillType;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "industrySkill")
	public Set<Question> getQuestions() {
		return this.questions;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}

}
